package sample.service.payment.exception;

public class PaymentNotFoundException extends Exception {

  public PaymentNotFoundException(String id) {
    super("Payment not found id: " + id);
  }

  public PaymentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
