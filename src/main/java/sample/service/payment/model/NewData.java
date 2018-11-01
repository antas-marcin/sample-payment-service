package sample.service.payment.model;

import java.util.UUID;

public class NewData {

  private Data.DataType type;
  private String organisationId;
  private Attributes attributes;

  public NewData(Data.DataType type, String organisationId, Attributes attributes) {
    this.type = type;
    this.organisationId = organisationId;
    this.attributes = attributes;
  }

  public Data toData() {
    return new Data(UUID.randomUUID().toString(), type, 0, this.organisationId, this.attributes);
  }
}
