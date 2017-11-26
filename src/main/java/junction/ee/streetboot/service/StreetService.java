package junction.ee.streetboot.service;

import junction.ee.streetboot.model.street.Coordinate;
import junction.ee.streetboot.model.street.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StreetService {
  private static final String LOCATION_DATA_PATH = "final_fucking_document.csv";
  private static final String PRECIPITATION_DATA_PATH = "zip_codes_with_precipitation.csv";

  @Autowired
  public StreetService() {
  }

  public List<Coordinate> getStreetCoordinates() {
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream is = classLoader.getResourceAsStream(LOCATION_DATA_PATH);
    Stream<String> lines = new BufferedReader(new InputStreamReader(is)).lines();
    List<Coordinate> collect = lines.map(StreetService::getCoordinate).collect(Collectors.toList());
    collect.forEach(this::assignPriorityToStreet);
    return collect;
  }

  private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

  public void assignPriorityToStreet(Coordinate coordinate) {
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream is = classLoader.getResourceAsStream(PRECIPITATION_DATA_PATH);
    Stream<String> lines = new BufferedReader(new InputStreamReader(is)).lines();
    lines.map(StreetService::getZipCode).forEach(e -> {
      if (e.getWater() < 45.0) {
        coordinate.setPriority(new Priority("blue"));
      } else if (e.getWater() > 10.0) {
        coordinate.setPriority(new Priority("green"));
      } else {
        coordinate.setPriority(new Priority("yellow"));
      }
    });
  }

  private static Coordinate getCoordinate(String line) {
    //Finland,Helsinki city,Palosuontie,07530,60.24506049999999, 24.9397343
    String[] split = line.split(",");
    return new Coordinate(split[4], split[5].replace(" ", ""), split[2], null);
  }

  private static ZipAndWater getZipCode(String line) {
    String[] split = line.split(",");
    String zipCode = split[0];
    double d = 0;
    for (int i = 1; i < split.length - 1; i++) {
      double d1 = Double.parseDouble(split[i]);
      d += d1;
    }
    return new ZipAndWater(zipCode, d);
  }

  private static class ZipAndWater {
    private String zipCode;
    private double water;

    public ZipAndWater(String zipCode, double water) {
      this.zipCode = zipCode;
      this.water = water;
    }

    public String getZipCode() {
      return zipCode;
    }

    public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
    }

    public double getWater() {
      return water;
    }

    public void setWater(double water) {
      this.water = water;
    }
  }
}
