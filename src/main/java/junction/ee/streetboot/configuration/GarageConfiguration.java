package junction.ee.streetboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class GarageConfiguration {

  private String garageApiUrl;

  public String getGarageApiUrl() {
    return garageApiUrl;
  }

  public void setGarageApiUrl(String garageApiUrl) {
    this.garageApiUrl = garageApiUrl;
  }
}
