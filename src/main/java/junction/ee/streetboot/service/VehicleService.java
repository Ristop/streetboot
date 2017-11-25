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

import junction.ee.streetboot.configuration.VehiclesConfiguration;
import junction.ee.streetboot.model.vehicle.Vehicle;
import junction.ee.streetboot.model.vehicle.VehicleList;

@Component
public class VehicleService {

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  private RestTemplate restTemplate;
  private VehiclesConfiguration configuration;

  @Autowired
  public VehicleService(RestTemplate restTemplate, VehiclesConfiguration configuration) {
    this.restTemplate = restTemplate;
    this.configuration = configuration;
  }

  public List<Vehicle> getVehicles() {
    return getVehiclesByTypeAndName(null, null);
  }

  public List<Vehicle> getVehiclesByType(String type) {
    return getVehiclesByTypeAndName(type, null);
  }

  public List<Vehicle> getVehiclesByName(String name) {
    return getVehiclesByTypeAndName(null, name);
  }

  public List<Vehicle> getVehiclesByTypeAndName(String type, String name) {
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(configuration.getVehiclesApiUrl());
    }
    catch (URISyntaxException e) {
      return Collections.emptyList();
    }

    if (type != null) {
      uriBuilder.addParameter("Type", type);
    }

    if (name != null) {
      uriBuilder.addParameter("Name", name);
    }

    try {
      VehicleList vehicleList = restTemplate.postForObject(uriBuilder.toString(), new HashMap<>(), VehicleList.class);
      List<Vehicle> rows = vehicleList.getRows();
      return new ArrayList<>(rows);
    }
    catch (ResourceAccessException e) {
      log.warn(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }
}