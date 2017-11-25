package junction.ee.streetboot.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import junction.ee.streetboot.configuration.RefuelConfiguration;
import junction.ee.streetboot.model.refuel.Refuel;
import junction.ee.streetboot.model.refuel.RefuelList;

@Component
public class RefuelService {

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  private RestTemplate restTemplate;
  private RefuelConfiguration configuration;

  @Autowired
  public RefuelService(RestTemplate restTemplate, RefuelConfiguration configuration) {
    this.restTemplate = restTemplate;
    this.configuration = configuration;
  }

  public List<Refuel> getVehileRefuelEventsByFuelCardNum(String fuelCardNum) {
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(configuration.getRefuelApiUrl());
    }
    catch (URISyntaxException e) {
      return Collections.emptyList();
    }

    if (fuelCardNum != null) {
      uriBuilder.addParameter("FuelCardNum", fuelCardNum);
    }

    try {
      RefuelList vehicleList = restTemplate.postForObject(uriBuilder.toString(), new HashMap<>(), RefuelList.class);
      List<Refuel> rows = vehicleList.getRows();
      return new ArrayList<>(rows);
    }
    catch (ResourceAccessException e) {
      log.warn(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }
}
