package junction.ee.streetboot.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import junction.ee.streetboot.model.location.Location;
import junction.ee.streetboot.model.refuel.Refuel;
import junction.ee.streetboot.model.vehicle.Vehicle;
import junction.ee.streetboot.model.wash.Wash;
import junction.ee.streetboot.service.LocationService;
import junction.ee.streetboot.service.RefuelService;
import junction.ee.streetboot.service.VehicleService;
import junction.ee.streetboot.service.WashService;

@RestController
public class VehicleRestController {

  private final VehicleService vehicleService;
  private final WashService washService;
  private final RefuelService refuelService;
  private final LocationService locationService;

  @Autowired
  public VehicleRestController(VehicleService vehicleService, WashService washService, RefuelService refuelService, LocationService locationService) {
    this.vehicleService = vehicleService;
    this.washService = washService;
    this.refuelService = refuelService;
    this.locationService = locationService;
  }

  @GetMapping("/api/vehicles")
  public ResponseEntity<List<Vehicle>> getVehicles() {
    return ResponseEntity.ok(vehicleService.getVehicles().stream().filter(vehicle -> vehicle.getFuelCardNum() != null).collect(Collectors.toList()));
  }

  @GetMapping("/api/vehicles/wash")
  public ResponseEntity<List<Wash>> getVehicleWashEvnet(@RequestParam("fuelCardNum") String fuelCardNum) {
    return ResponseEntity.ok(washService.getVehileWashEventsByFuelCardNum(fuelCardNum));
  }

  @GetMapping("/api/vehicles/refuel")
  public ResponseEntity<List<Refuel>> getVehicleRefuelEvents(@RequestParam("fuelCardNum") String fuelCardNum) {
    List<Refuel> vehileRefuelEventsByFuelCardNum = refuelService.getVehileRefuelEventsByFuelCardNum(fuelCardNum);

    double average = vehileRefuelEventsByFuelCardNum.stream().mapToDouble(Refuel::getFuelVolume).sum() / vehileRefuelEventsByFuelCardNum.size();
    System.out.println(average);

    Collections.sort(vehileRefuelEventsByFuelCardNum);

    return ResponseEntity.ok(new ArrayList<>(vehileRefuelEventsByFuelCardNum));
  }

  @GetMapping("/api/vehicles/location")
  public ResponseEntity<List<Location>> getVehicleLocation(@RequestParam("name") String name) {
    List<Location> vehileRefuelEventsByFuelCardNum = locationService.getVehicleLocation(
        name, 10000,
        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
        LocalDateTime.now().minusYears(1).toEpochSecond(ZoneOffset.UTC));

    return ResponseEntity.ok(new ArrayList<>(vehileRefuelEventsByFuelCardNum));
  }

  @GetMapping("/api/vehicles/locationbydates")
  public ResponseEntity<List<Location>> getVehicleLocationByStartAndEndDate(
      @RequestParam("name") String name, @RequestParam("startDate") Long startDate, @RequestParam("endDate") Long endDate) {
    System.out.println(startDate + " " + endDate);
    List<Location> vehileRefuelEventsByFuelCardNum = locationService.getVehicleLocation(
        name, 10000, endDate, startDate);

    return ResponseEntity.ok(new ArrayList<>(vehileRefuelEventsByFuelCardNum));
  }

}
