package sample.service.payment.model;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;

public class Payment {

  @Id
  private String id;
  private String type;
  private Integer version;
  @SerializedName("organisation_id")
  private String organisationId;
  private Attributes attributes;

  public Payment(String id, Integer version, String organisationId, Attributes attributes) {
    this.id = id;
    this.type = "Payment";
    this.version = version;
    this.organisationId = organisationId;
    this.attributes = attributes;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Integer getVersion() {
    return version;
  }

  public String getOrganisationId() {
    return organisationId;
  }

  public Attributes getAttributes() {
    return attributes;
  }
}
