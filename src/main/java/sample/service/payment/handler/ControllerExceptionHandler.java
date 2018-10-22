package sample.service.payment.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sample.service.payment.exception.PaymentNotFoundException;
import sample.service.payment.exception.PaymentNotSavedException;

@ControllerAdvice
public class ControllerExceptionHandler {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = { PaymentNotFoundException.class, PaymentNotSavedException.class })
  public void handlePaymentExceptions(Exception e) {
    LOG.info(e.getMessage());
  }

}
