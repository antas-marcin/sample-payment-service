package sample.service.payment.model;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargesInformation {

  String bearerCode;
  List<Charge> senderCharges;
  String receiverChargesAmount;
  String receiverChargesCurrency;
}
