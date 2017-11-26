package junction.ee.streetboot.model.street;

public class Coordinate {
  private String latitude;
  private String longitude;
  private String streetName;
  private Priority priority;

  public Coordinate(String latitude, String longitude, String streetName, Priority priority) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.streetName = streetName;
    this.priority = priority;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getStreetName() {
    return streetName;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }
}
