package sample.service.payment.model;

import com.google.gson.annotations.SerializedName;

public class SponsorParty {

  @SerializedName("account_number")
  private String accountNumber;
  @SerializedName("bank_id")
  private String bankId;
  @SerializedName("bank_id_code")
  private String bankIdCode;

  public SponsorParty(String accountNumber, String bankId, String bankIdCode) {
    this.accountNumber = accountNumber;
    this.bankId = bankId;
    this.bankIdCode = bankIdCode;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getBankId() {
    return bankId;
  }

  public String getBankIdCode() {
    return bankIdCode;
  }
}
