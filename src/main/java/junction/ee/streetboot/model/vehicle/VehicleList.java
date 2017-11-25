package junction.ee.streetboot.model.vehicle;

import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class VehicleList {

  private List<Vehicle> rows;

  public List<Vehicle> getRows() {
    return rows;
  }

  public void setRows(Vehicle[] rows) {
    this.rows = Arrays.asList(rows);
  }

}
