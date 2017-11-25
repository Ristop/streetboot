package junction.ee.streetboot.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import junction.ee.streetboot.configuration.WashConfiguration;
import junction.ee.streetboot.model.wash.Wash;
import junction.ee.streetboot.model.wash.WashList;

@Component
public class WashService {

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  private RestTemplate restTemplate;
  private WashConfiguration configuration;

  public WashService(RestTemplate restTemplate, WashConfiguration configuration) {
    this.restTemplate = restTemplate;
    this.configuration = configuration;
  }

  public List<Wash> getVehileWashEventsByFuelCardNum(String fuelCardNum) {
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(configuration.getWashApiUrl());
    }
    catch (URISyntaxException e) {
      return Collections.emptyList();
    }

    if (fuelCardNum != null) {
      uriBuilder.addParameter("FuelCardNum", fuelCardNum);
    }

    try {
      WashList vehicleList = restTemplate.postForObject(uriBuilder.toString(), new HashMap<>(), WashList.class);
      List<Wash> rows = vehicleList.getRows();
      return new ArrayList<>(rows);
    }
    catch (ResourceAccessException e) {
      log.warn(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }
}
