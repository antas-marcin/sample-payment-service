package sample.service.payment.repository;

import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import sample.service.payment.model.Payment;

@Component
public class PaymentMongoEventListener extends AbstractMongoEventListener<Payment> {

  @Override
  public void onBeforeSave(BeforeSaveEvent<Payment> event) {
    if (event.getSource().getId() == null) {
      // generate UUID RFC 4122 ID
      event.getSource().setId(UUID.randomUUID().toString());
    }
  }
}
