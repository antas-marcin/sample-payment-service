package sample.service.payment.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForexExchange {

  String contractReference;
  String exchangeRate;
  String originalAmount;
  String originalCurrency;
}
