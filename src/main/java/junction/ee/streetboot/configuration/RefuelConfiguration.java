package junction.ee.streetboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class RefuelConfiguration {

  private String refuelApiUrl;

  public String getRefuelApiUrl() {
    return refuelApiUrl;
  }

  public void setRefuelApiUrl(String refuelApiUrl) {
    this.refuelApiUrl = refuelApiUrl;
  }
}