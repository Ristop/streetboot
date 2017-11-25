package junction.ee.streetboot.model.refuel;

import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Refuel implements Comparable<Refuel> {

  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d uuuu  hh:mm");

  @JsonProperty("FuelCardNum")
  private String fuelCardNum;
  @JsonProperty("FuelQuality")
  private String fuelQuality;
  @JsonProperty("FuelVolume")
  private Double fuelVolume;
  @JsonProperty("Km")
  private Double km;
  @JsonProperty("Station")
  private String station;
  @JsonProperty("Time")
  private Long time;

  public String getFuelCardNum() {
    return fuelCardNum;
  }

  public void setFuelCardNum(String fuelCardNum) {
    this.fuelCardNum = fuelCardNum;
  }

  public String getFuelQuality() {
    return fuelQuality;
  }

  public void setFuelQuality(String fuelQuality) {
    this.fuelQuality = fuelQuality;
  }

  public Double getFuelVolume() {
    return fuelVolume;
  }

  public void setFuelVolume(Double fuelVolume) {
    this.fuelVolume = fuelVolume;
  }

  public Double getKm() {
    return km;
  }

  public void setKm(String km) {
    this.km = Double.parseDouble(km);
  }

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }

  public Long getTime() {
    return this.time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  @Override
  public int compareTo(Refuel o) {
    return this.getTime().compareTo(o.getTime());
  }
}
