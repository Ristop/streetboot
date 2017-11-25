package junction.ee.streetboot.model.wash;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wash {

  @JsonProperty("Station")
  private String station;
  @JsonProperty("FuelCardNum")
  private String fuelCardNum;
  @JsonProperty("Time")
  private Long time;
  @JsonProperty("Quality")
  private Integer quality;

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }

  public String getFuelCardNum() {
    return fuelCardNum;
  }

  public void setFuelCardNum(String fuelCardNum) {
    this.fuelCardNum = fuelCardNum;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Integer getQuality() {
    return quality;
  }

  public void setQuality(Integer quality) {
    this.quality = quality;
  }
}
