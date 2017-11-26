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

import junction.ee.streetboot.configuration.GarageBySEHIDConfiguration;
import junction.ee.streetboot.model.garage.GarageBySEHIID;
import junction.ee.streetboot.model.garage.GarageBySEHIIDList;

@Component
public class GarageBySEHIIDService {

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  private RestTemplate restTemplate;
  private GarageBySEHIDConfiguration configuration;

  @Autowired
  public GarageBySEHIIDService(RestTemplate restTemplate, GarageBySEHIDConfiguration configuration) {
    this.restTemplate = restTemplate;
    this.configuration = configuration;
  }

  public List<GarageBySEHIID> getVehicleGarageBySehiId(String sehiId) {
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(configuration.getGarageBySEHIIDApiUrl());
    }
    catch (URISyntaxException e) {
      return Collections.emptyList();
    }

    uriBuilder.addParameter("SEHIID", sehiId);

    try {
      GarageBySEHIIDList locationList = restTemplate.postForObject(uriBuilder.toString(), new HashMap<>(), GarageBySEHIIDList.class);
      List<GarageBySEHIID> rows = locationList.getRows();
      return new ArrayList<>(rows);
    }
    catch (ResourceAccessException e) {
      log.warn(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }
}