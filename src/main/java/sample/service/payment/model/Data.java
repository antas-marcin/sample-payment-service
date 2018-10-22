package sample.service.payment.model;

import java.util.List;

public class Data {

  private List<Payment> data;

  public Data() {
  }

  public Data(List<Payment> data) {
    this.data = data;
  }

  public List<Payment> getData() {
    return data;
  }
}
