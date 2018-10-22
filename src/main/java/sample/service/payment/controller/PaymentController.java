package sample.service.payment.controller;

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
import sample.service.payment.model.Payment;
import sample.service.payment.service.PaymentService;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @GetMapping("/payment")
  public Data listPayments() {
    return paymentService.getPayments();
  }

  @GetMapping("/payment/{id}")
  public Data getPayment(@PathVariable String id) throws PaymentNotFoundException {
    return paymentService.getPayment(id);
  }

  @PostMapping("/payment")
  public Data savePayment(@RequestBody Payment payment) throws PaymentNotSavedException {
    return paymentService.savePayment(payment);
  }

  @PutMapping("/payment/{id}")
  public Data updatePayment(@PathVariable String id, @RequestBody Payment payment) throws PaymentNotFoundException {
    return paymentService.updatePayment(id, payment);
  }

  @DeleteMapping("/payment/{id}")
  public Data deletePayment(@PathVariable String id) throws PaymentNotFoundException {
    return paymentService.deletePayment(id);
  }

}
