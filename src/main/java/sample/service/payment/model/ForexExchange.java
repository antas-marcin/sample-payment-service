package sample.service.payment.model;

import com.google.gson.annotations.SerializedName;

public class ForexExchange {

  @SerializedName("contract_reference")
  private String contractReference;
  @SerializedName("exchange_rate")
  private String exchangeRate;
  @SerializedName("original_amount")
  private String originalAmount;
  @SerializedName("original_currency")
  private String originalCurrency;

  public ForexExchange(String contractReference, String exchangeRate, String originalAmount, String originalCurrency) {
    this.contractReference = contractReference;
    this.exchangeRate = exchangeRate;
    this.originalAmount = originalAmount;
    this.originalCurrency = originalCurrency;
  }

  public String getContractReference() {
    return contractReference;
  }

  public String getExchangeRate() {
    return exchangeRate;
  }

  public String getOriginalAmount() {
    return originalAmount;
  }

  public String getOriginalCurrency() {
    return originalCurrency;
  }
}
