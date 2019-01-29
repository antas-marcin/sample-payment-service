package sample.service.payment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonApiResource(type = "Payment", resourcePath = "v1/payments")
public class Payment {

  public enum DataType {
    Payment
  }

  @Id
  @JsonApiId
  String id;
  @NotNull
  DataType type;
  @PositiveOrZero
  Integer version;
  @NotBlank
  String amount;
  @NotBlank
  @Size(min = 3, message = "OrganisationId must be set")
  String organisationId;
  @Valid
  @NotNull
  Party beneficiaryParty;
  @NotBlank
  String currency;
  ChargesInformation chargesInformation;
  @Valid
  @NotNull
  Party debtorParty;
  String endToEndReference;
  ForexExchange fx;
  String numericReference;
  String paymentId;
  String paymentPurpose;
  String paymentScheme;
  String paymentType;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @NotNull
  Date processingDate;
  @NotBlank
  String reference;
  String schemePaymentSubType;
  @NotBlank
  String schemePaymentType;
  SponsorParty sponsorParty;

  public Payment() {
    this.type = DataType.Payment;
  }
}
