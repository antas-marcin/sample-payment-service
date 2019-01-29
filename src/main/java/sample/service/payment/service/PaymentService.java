package sample.service.payment.service;

import io.crnk.core.exception.ForbiddenException;
import io.crnk.core.exception.ResourceNotFoundException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.service.payment.model.Payment;
import sample.service.payment.repository.PaymentMongoRepository;

@Service
public class PaymentService {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PaymentMongoRepository repository;

  public List<Payment> getPayments() {
    LOG.info("[getPayments] Get all payments");
    return repository.findAll();
  }

  public Payment getPayment(String id) {
    LOG.info("[getPayment] Get payment with id: {}", id);
    return repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Payment with id:" + id + " not found"));
  }

  public Payment savePayment(Payment payment) {
    LOG.info("[savePayment] Save payment data invoked");
    if (StringUtils.isNotEmpty(payment.getId())) {
      throw new ForbiddenException("Cannot save payment with client generated ID. ID must be empty.");
    }
    return repository.save(payment);
  }

  public Payment updatePayment(Payment payment) {
    LOG.info("[updatePayment] Update payment with id: {}", payment.getId());

    Integer lastVersion = repository
            .findById(payment.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Payment with id:" + payment.getId() + " not found"))
            .getVersion();

    return repository.save(createPayment(payment, lastVersion));
  }

  private Payment createPayment(Payment payment, Integer lastVersion) {
    payment.setVersion(lastVersion + 1);
    return payment;
  }

  public void deletePayment(String id) {
    LOG.info("[deletePayment] Delete payment with id: {}", id);
    repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Payment with id:" + id + " not found"));
    repository.deleteById(id);
  }

}
