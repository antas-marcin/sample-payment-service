package sample.service.payment.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.service.payment.exception.PaymentNotFoundException;
import sample.service.payment.exception.PaymentNotSavedException;
import sample.service.payment.model.Data;
import sample.service.payment.model.NewData;
import sample.service.payment.repository.DataMongoRepository;

@Service
public class PaymentService {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private DataMongoRepository repository;

  public List<Data> getPayments() {
    LOG.info("[getPayments] Get all payments from db");
    return repository
            .findByType(Data.DataType.Payment);
  }

  public Data getPayment(String id) throws PaymentNotFoundException {
    LOG.info("[getPayment] Get payment with id: {}", id);
    return repository
            .findById(id)
            .orElseThrow(() -> new PaymentNotFoundException(id));
  }

  public Data savePayment(NewData payment) throws PaymentNotSavedException {
    LOG.info("[savePayment] Save payment data invoked");
    return repository
            .save(payment.toData());
  }

  public Data updatePayment(String id, Data payment) throws PaymentNotFoundException {
    LOG.info("[updatePayment] Update payment with id: {}", payment.getId());

    Integer lastVersion = repository
            .findById(id)
            .orElseThrow(() -> new PaymentNotFoundException(id))
            .getVersion();

    return repository
            .save(new Data(payment.getId(), payment.getType(), lastVersion + 1, payment.getOrganisationId(), payment.getAttributes()));
  }

  public void deletePayment(String id) throws PaymentNotFoundException {
    repository
            .findById(id)
            .orElseThrow(() -> new PaymentNotFoundException(id));
    LOG.info("[deletePayment] Delete payment with id: {}", id);
    repository.deleteById(id);
  }

}
