package sample.service.payment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.service.payment.exception.PaymentNotFoundException;
import sample.service.payment.exception.PaymentNotSavedException;
import sample.service.payment.model.Data;
import sample.service.payment.model.NewData;
import sample.service.payment.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentControllerV1 {

  @Autowired
  private PaymentService paymentService;

  @GetMapping()
  public List<Data> listPayments() {
    return paymentService.getPayments();
  }

  @GetMapping("/{id}")
  public Data getPayment(@PathVariable String id) throws PaymentNotFoundException {
    return paymentService.getPayment(id);
  }

  @PostMapping()
  public Data savePayment(@RequestBody NewData payment) throws PaymentNotSavedException {
    return paymentService.savePayment(payment);
  }

  @PutMapping("/{id}")
  public Data updatePayment(@PathVariable String id, @RequestBody Data payment) throws PaymentNotFoundException {
    return paymentService.updatePayment(id, payment);
  }

  @DeleteMapping("/{id}")
  public void deletePayment(@PathVariable String id) throws PaymentNotFoundException {
    paymentService.deletePayment(id);
  }

}
