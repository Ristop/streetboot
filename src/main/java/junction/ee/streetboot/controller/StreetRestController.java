package junction.ee.streetboot.controller;

import junction.ee.streetboot.model.street.Coordinate;
import junction.ee.streetboot.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StreetRestController {

  private final StreetService streetService;

  @Autowired
  public StreetRestController(StreetService streetService) {
    this.streetService = streetService;
  }

  @GetMapping("/api/street/coordinates")
  public ResponseEntity<List<Coordinate>> getStreetCoordinates() {
    List<Coordinate> streetCoordinates = streetService.getStreetCoordinates();
    return ResponseEntity.ok(streetCoordinates);
  }
}
