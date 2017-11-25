package junction.ee.streetboot.configuration;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class streetBootApiIntercpetor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    HttpHeaders headers = request.getHeaders();
    headers.add("appkey", "f848dba8-adc9-45e5-9771-b51b0ffa700a");
    headers.add("x-thingworx-session", "true");
    return execution.execute(request, body);
  }

}
