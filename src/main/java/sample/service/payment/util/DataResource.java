package sample.service.payment.util;

import java.util.List;
import sample.service.payment.model.Data;

public class DataResource {

  private List<Data> data;

  public DataResource(List<Data> data) {
    this.data = data;
  }

  public List<Data> getData() {
    return data;
  }
}
