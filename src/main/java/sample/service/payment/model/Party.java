package sample.service.payment.model;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Party {

  @NotBlank
  String accountName;
  @NotBlank
  String accountNumber;
  @NotBlank
  String accountNumberCode;
  Integer accountType;
  String address;
  @NotBlank
  String bankId;
  String bankIdCode;
  String name;
}
