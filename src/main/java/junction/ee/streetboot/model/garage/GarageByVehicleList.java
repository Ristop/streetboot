package junction.ee.streetboot.model.garage;

import java.util.Arrays;
import java.util.List;

public class GarageByVehicleList {

  private List<GarageByVehicle> rows;

  public List<GarageByVehicle> getRows() {
    return rows;
  }

  public void setRows(GarageByVehicle[] rows) {
    this.rows = Arrays.asList(rows);
  }
}
