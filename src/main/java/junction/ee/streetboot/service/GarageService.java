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

import junction.ee.streetboot.configuration.GarageConfiguration;
import junction.ee.streetboot.model.garage.Garage;
import junction.ee.streetboot.model.garage.GarageList;

@Component
public class GarageService {

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  private RestTemplate restTemplate;
  private GarageConfiguration configuration;

  @Autowired
  public GarageService(RestTemplate restTemplate, GarageConfiguration configuration) {
    this.restTemplate = restTemplate;
    this.configuration = configuration;
  }

  public List<Garage> getVehileGarageByName(String name) {
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(configuration.getGarageApiUrl());
    }
    catch (URISyntaxException e) {
      return Collections.emptyList();
    }

    uriBuilder.addParameter("Name", name);

    try {
      GarageList vehicleList = restTemplate.postForObject(uriBuilder.toString(), new HashMap<>(), GarageList.class);
      List<Garage> rows = vehicleList.getRows();
      return new ArrayList<>(rows);
    }
    catch (ResourceAccessException e) {
      log.warn(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }
}