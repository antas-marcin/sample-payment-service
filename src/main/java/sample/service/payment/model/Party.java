package sample.service.payment.model;

import com.google.gson.annotations.SerializedName;

public class Party {

  @SerializedName("account_name")
  private String accountName;
  @SerializedName("account_number")
  private String accountNumber;
  @SerializedName("account_number_code")
  private String accountNumberCode;
  @SerializedName("account_type")
  private Integer accountType;
  private String address;
  @SerializedName("bank_id")
  private String bankId;
  @SerializedName("bank_id_code")
  private String bankIdCode;
  private String name;

  public Party(String accountName, String accountNumber, String accountNumberCode, Integer accountType, String address, String bankId,
          String bankIdCode, String name) {
    this.accountName = accountName;
    this.accountNumber = accountNumber;
    this.accountNumberCode = accountNumberCode;
    this.accountType = accountType;
    this.address = address;
    this.bankId = bankId;
    this.bankIdCode = bankIdCode;
    this.name = name;
  }

  public String getAccountName() {
    return accountName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountNumberCode() {
    return accountNumberCode;
  }

  public Integer getAccountType() {
    return accountType;
  }

  public String getAddress() {
    return address;
  }

  public String getBankId() {
    return bankId;
  }

  public String getBankIdCode() {
    return bankIdCode;
  }

  public String getName() {
    return name;
  }
}
