package junction.ee.streetboot.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class LocationConfiguration {

  private String locationApiUrl;

  public String getLocationApiUrl() {
    return locationApiUrl;
  }

  public void setLocationApiUrl(String locationApiUrl) {
    this.locationApiUrl = locationApiUrl;
  }
}
