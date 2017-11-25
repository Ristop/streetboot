package junction.ee.streetboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class VehiclesConfiguration {

  private String vehiclesApiUrl;

  public String getVehiclesApiUrl() {
    return vehiclesApiUrl;
  }

  public void setVehiclesApiUrl(String vehiclesApiUrl) {
    this.vehiclesApiUrl = vehiclesApiUrl;
  }
}
