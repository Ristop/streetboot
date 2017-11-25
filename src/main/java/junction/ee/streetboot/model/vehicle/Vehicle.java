package junction.ee.streetboot.model.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {

  @JsonProperty("Description")
  private String description;
  @JsonProperty("FuelCardNum")
  private String fuelCardNum;
  @JsonProperty("Name")
  private String name;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFuelCardNum() {
    return fuelCardNum;
  }

  public void setFuelCardNum(String fuelCardNum) {
    this.fuelCardNum = fuelCardNum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "description='" + description + '\'' +
        ", fuelCardNum='" + fuelCardNum + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
