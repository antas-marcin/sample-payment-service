package sample.service.payment.model;

import com.google.gson.annotations.SerializedName;

public class Attributes {

  private String amount;
  @SerializedName("beneficiary_party")
  private Party beneficiaryParty;
  @SerializedName("charges_information")
  private ChargesInformation chargesInformation;
  private String currency;
  @SerializedName("debtor_party")
  private Party debtorParty;
  @SerializedName("end_to_end_reference")
  private String endToEndReference;
  private ForexExchange fx;
  @SerializedName("numeric_reference")
  private String numericReference;
  @SerializedName("payment_id")
  private String paymentId;
  @SerializedName("payment_purpose")
  private String paymentPurpose;
  @SerializedName("payment_scheme")
  private String paymentScheme;
  @SerializedName("payment_type")
  private String paymentType;
  @SerializedName("processing_date")
  private String processingDate;
  private String reference;
  @SerializedName("scheme_payment_sub_type")
  private String schemePaymentSubType;
  @SerializedName("scheme_payment_type")
  private String schemePaymentType;
  @SerializedName("sponsor_party")
  private SponsorParty sponsorParty;

  public Attributes(String amount, Party beneficiaryParty, ChargesInformation chargesInformation, String currency,
          Party debtorParty, String endToEndReference, ForexExchange fx, String numericReference, String paymentId, String paymentPurpose,
          String paymentScheme, String paymentType, String processingDate, String reference, String schemePaymentSubType, String schemePaymentType,
          SponsorParty sponsorParty) {
    this.amount = amount;
    this.beneficiaryParty = beneficiaryParty;
    this.chargesInformation = chargesInformation;
    this.currency = currency;
    this.debtorParty = debtorParty;
    this.endToEndReference = endToEndReference;
    this.fx = fx;
    this.numericReference = numericReference;
    this.paymentId = paymentId;
    this.paymentPurpose = paymentPurpose;
    this.paymentScheme = paymentScheme;
    this.paymentType = paymentType;
    this.processingDate = processingDate;
    this.reference = reference;
    this.schemePaymentSubType = schemePaymentSubType;
    this.schemePaymentType = schemePaymentType;
    this.sponsorParty = sponsorParty;
  }

  public String getAmount() {
    return amount;
  }

  public Party getBeneficiaryParty() {
    return beneficiaryParty;
  }

  public ChargesInformation getChargesInformation() {
    return chargesInformation;
  }

  public String getCurrency() {
    return currency;
  }

  public Party getDebtorParty() {
    return debtorParty;
  }

  public String getEndToEndReference() {
    return endToEndReference;
  }

  public ForexExchange getFx() {
    return fx;
  }

  public String getNumericReference() {
    return numericReference;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public String getPaymentPurpose() {
    return paymentPurpose;
  }

  public String getPaymentScheme() {
    return paymentScheme;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public String getProcessingDate() {
    return processingDate;
  }

  public String getReference() {
    return reference;
  }

  public String getSchemePaymentSubType() {
    return schemePaymentSubType;
  }

  public String getSchemePaymentType() {
    return schemePaymentType;
  }

  public SponsorParty getSponsorParty() {
    return sponsorParty;
  }
}
