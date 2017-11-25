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

import junction.ee.streetboot.configuration.LocationConfiguration;
import junction.ee.streetboot.model.location.Location;
import junction.ee.streetboot.model.location.LocationList;

@Component
public class LocationService {

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  private RestTemplate restTemplate;
  private LocationConfiguration configuration;

  @Autowired
  public LocationService(RestTemplate restTemplate, LocationConfiguration configuration) {
    this.restTemplate = restTemplate;
    this.configuration = configuration;
  }

  public List<Location> getVehicleLocation(String name, int maxItems, Long endDate, Long startDate) {
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(configuration.getLocationApiUrl());
    }
    catch (URISyntaxException e) {
      return Collections.emptyList();
    }

    uriBuilder.addParameter("Name", name);
    uriBuilder.addParameter("maxItems", String.valueOf(maxItems));
    uriBuilder.addParameter("endDate", String.valueOf(endDate)+"000");
    uriBuilder.addParameter("startDate", String.valueOf(startDate)+"000");

    try {
      LocationList locationList = restTemplate.postForObject(uriBuilder.toString(), new HashMap<>(), LocationList.class);
      List<Location> rows = locationList.getRows();
      return new ArrayList<>(rows);
    }
    catch (ResourceAccessException e) {
      log.warn(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }
}