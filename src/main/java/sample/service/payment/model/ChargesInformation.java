package sample.service.payment.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ChargesInformation {

  @SerializedName("bearer_code")
  private String bearerCode;
  @SerializedName("sender_charges")
  private List<Charge> senderCharges;
  @SerializedName("receiver_charges_amount")
  private String receiverChargesAmount;
  @SerializedName("receiver_charges_currency")
  private String receiverChargesCurrency;

  public ChargesInformation(String bearerCode, List<Charge> senderCharges, String receiverChargesAmount, String receiverChargesCurrency) {
    this.bearerCode = bearerCode;
    this.senderCharges = senderCharges;
    this.receiverChargesAmount = receiverChargesAmount;
    this.receiverChargesCurrency = receiverChargesCurrency;
  }

  public String getBearerCode() {
    return bearerCode;
  }

  public List<Charge> getSenderCharges() {
    return senderCharges;
  }

  public String getReceiverChargesAmount() {
    return receiverChargesAmount;
  }

  public String getReceiverChargesCurrency() {
    return receiverChargesCurrency;
  }
}
