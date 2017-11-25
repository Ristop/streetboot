package junction.ee.streetboot.configuration;

import java.util.Collections;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "streetboot")
public class StreetBootConfiguration {

  @Bean
  public RestTemplate getRestTemplate() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
        HttpClientBuilder.create().build());

    clientHttpRequestFactory.setConnectTimeout(2_000);
    clientHttpRequestFactory.setConnectionRequestTimeout(2_000);
    clientHttpRequestFactory.setReadTimeout(10_000);

    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    restTemplate.setInterceptors(Collections.singletonList(new streetBootApiIntercpetor()));
    return restTemplate;
  }

}
