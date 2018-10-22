package sample.service.payment.exception;

public class PaymentNotSavedException extends Exception {

  public PaymentNotSavedException(String id) {
    super("Payment could not be saved id: " + id);
  }

  public PaymentNotSavedException(String message, Throwable cause) {
    super(message, cause);
  }
}
