package junction.ee.streetboot.model.garage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GarageBySEHIID {

  @JsonProperty("SSUMNOVAT")
  private String ssumnovat;
  @JsonProperty("UNITPR")
  private String unitpr;
  @JsonProperty("NAME")
  private String name;
  @JsonProperty("RTYPE")
  private String rtype;
  @JsonProperty("NUM")
  private String num;
  @JsonProperty("ITEM")
  private String item;
  @JsonProperty("BILLD")
  private String billd;
  @JsonProperty("TSUM")
  private String tsum;
  @JsonProperty("IMPCODE")
  private String impCode;
  @JsonProperty("NOTE")
  private String note;
  @JsonProperty("SEHIID")
  private String sehiId;
  @JsonProperty("SERVD")
  private String servd;

  public String getSsumnovat() {
    return ssumnovat;
  }

  public void setSsumnovat(String ssumnovat) {
    this.ssumnovat = ssumnovat;
  }

  public String getUnitpr() {
    return unitpr;
  }

  public void setUnitpr(String unitpr) {
    this.unitpr = unitpr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRtype() {
    return rtype;
  }

  public void setRtype(String rtype) {
    this.rtype = rtype;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getBilld() {
    return billd;
  }

  public void setBilld(String billd) {
    this.billd = billd;
  }

  public String getTsum() {
    return tsum;
  }

  public void setTsum(String tsum) {
    this.tsum = tsum;
  }

  public String getImpCode() {
    return impCode;
  }

  public void setImpCode(String impCode) {
    this.impCode = impCode;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getSehiId() {
    return sehiId;
  }

  public void setSehiId(String sehiId) {
    this.sehiId = sehiId;
  }

  public String getServd() {
    return servd;
  }

  public void setServd(String servd) {
    this.servd = servd;
  }
}