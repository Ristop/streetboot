package junction.ee.streetboot.model.garage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GarageByVehicle {

  @JsonProperty("SERVD")
  private String servd;
  @JsonProperty("SEHIID")
  private String sehiId;
  @JsonProperty("IMPCODE")
  private String impCode;

  public String getServd() {
    return servd;
  }

  public void setServd(String servd) {
    this.servd = servd;
  }

  public String getSehiId() {
    return sehiId;
  }

  public void setSehiId(String sehiId) {
    this.sehiId = sehiId;
  }

  public String getImpCode() {
    return impCode;
  }

  public void setImpCode(String impCode) {
    this.impCode = impCode;
  }
}
