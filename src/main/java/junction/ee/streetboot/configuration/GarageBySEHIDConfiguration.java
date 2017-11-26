package junction.ee.streetboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class GarageBySEHIDConfiguration {

  private String garageBySEHIIDApiUrl;

  public String getGarageBySEHIIDApiUrl() {
    return garageBySEHIIDApiUrl;
  }

  public void setGarageBySEHIIDApiUrl(String garageBySEHIIDApiUrl) {
    this.garageBySEHIIDApiUrl = garageBySEHIIDApiUrl;
  }
}
