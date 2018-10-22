package sample.service.payment.service;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.service.payment.exception.PaymentNotFoundException;
import sample.service.payment.exception.PaymentNotSavedException;
import sample.service.payment.model.Data;
import sample.service.payment.model.Payment;
import sample.service.payment.repository.protocol.PaymentRepository;

@Service
public class PaymentService {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PaymentRepository paymentRepository;

  public Data getPayments() {
    LOG.info("[getPayments] Get all payments from db");
    return toResult(paymentRepository.findAll());
  }

  public Data getPayment(String id) throws PaymentNotFoundException {
    LOG.info("[getPayment] Get payment with id: {}", id);
    return toResult(Arrays.asList(paymentRepository.find(id).orElseThrow(() -> new PaymentNotFoundException(id))));
  }

  public Data savePayment(Payment payment) throws PaymentNotSavedException {
    LOG.info("[savePayment] Save payment with id: {}", payment.getId());
    return toResult(Arrays.asList(paymentRepository.save(payment).orElseThrow(() -> new PaymentNotSavedException(payment.getId()))));
  }

  public Data updatePayment(String id, Payment payment) throws PaymentNotFoundException {
    LOG.info("[updatePayment] Update payment with id: {}", payment.getId());
    return toResult(Arrays.asList(paymentRepository.update(id, payment).orElseThrow(() -> new PaymentNotFoundException(id))));
  }

  public Data deletePayment(String id) throws PaymentNotFoundException {
    LOG.info("[deletePayment] Delete payment with id: {}", id);
    return toResult(Arrays.asList(paymentRepository.delete(id).orElseThrow(() -> new PaymentNotFoundException(id))));
  }

  private Data toResult(List<Payment> payments) {
    return new Data(payments);
  }
}
