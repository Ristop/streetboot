package junction.ee.streetboot.model.garage;

import java.util.Arrays;
import java.util.List;

public class GarageList {

  private List<Garage> rows;

  public List<Garage> getRows() {
    return rows;
  }

  public void setRows(Garage[] rows) {
    this.rows = Arrays.asList(rows);
  }
}
