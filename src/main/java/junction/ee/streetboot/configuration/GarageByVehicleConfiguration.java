package junction.ee.streetboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class GarageByVehicleConfiguration {

  private String garageByVehicleApiUrl;

  public String getGarageByVehicleApiUrl() {
    return garageByVehicleApiUrl;
  }

  public void setGarageByVehicleApiUrl(String garageByVehicleApiUrl) {
    this.garageByVehicleApiUrl = garageByVehicleApiUrl;
  }
}
