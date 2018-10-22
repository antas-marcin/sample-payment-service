package sample.service.payment.model;

public class Charge {

  private String amount;
  private String currency;

  public Charge(String amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public String getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }
}
