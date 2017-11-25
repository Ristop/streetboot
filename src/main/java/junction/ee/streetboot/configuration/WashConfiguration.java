package junction.ee.streetboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class WashConfiguration {

  private String washApiUrl;

  public String getWashApiUrl() {
    return washApiUrl;
  }

  public void setWashApiUrl(String washApiUrl) {
    this.washApiUrl = washApiUrl;
  }
}
