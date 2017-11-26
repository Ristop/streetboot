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

import junction.ee.streetboot.configuration.GarageByVehicleConfiguration;
import junction.ee.streetboot.model.garage.GarageByVehicle;
import junction.ee.streetboot.model.garage.GarageByVehicleList;

@Component
public class GarageByVehicleService {

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  private RestTemplate restTemplate;
  private GarageByVehicleConfiguration configuration;

  @Autowired
  public GarageByVehicleService(RestTemplate restTemplate, GarageByVehicleConfiguration configuration) {
    this.restTemplate = restTemplate;
    this.configuration = configuration;
  }

  public List<GarageByVehicle> getVehicleGarage(String name) {
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(configuration.getGarageByVehicleApiUrl());
    }
    catch (URISyntaxException e) {
      return Collections.emptyList();
    }

    uriBuilder.addParameter("Name", name);

    try {
      GarageByVehicleList locationList = restTemplate.postForObject(uriBuilder.toString(), new HashMap<>(), GarageByVehicleList.class);
      List<GarageByVehicle> rows = locationList.getRows();
      return new ArrayList<>(rows);
    }
    catch (ResourceAccessException e) {
      log.warn(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }
}
