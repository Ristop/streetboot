package junction.ee.streetboot.model.garage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Garage {

  @JsonProperty("TSUM")
  private Double tsum;
  @JsonProperty("ITEM")
  private String item;
  @JsonProperty("NOTE")
  private String note;
  @JsonProperty("RTYPE")
  private String rtype;
  @JsonProperty("BILLD")
  private Long billd;
  @JsonProperty("IMPCODE")
  private String impcode;
  @JsonProperty("SERVD")
  private Long servd;
  @JsonProperty("NAME")
  private String name;

  public Double getTsum() {
    return tsum;
  }

  public void setTsum(Double tsum) {
    this.tsum = tsum;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getRtype() {
    return rtype;
  }

  public void setRtype(String rtype) {
    this.rtype = rtype;
  }

  public Long getBilld() {
    return billd;
  }

  public void setBilld(Long billd) {
    this.billd = billd;
  }

  public String getImpcode() {
    return impcode;
  }

  public void setImpcode(String impcode) {
    this.impcode = impcode;
  }

  public Long getServd() {
    return servd;
  }

  public void setServd(Long servd) {
    this.servd = servd;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
