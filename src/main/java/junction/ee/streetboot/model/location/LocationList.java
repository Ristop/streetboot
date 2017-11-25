package junction.ee.streetboot.model.location;

import java.util.Arrays;
import java.util.List;

public class LocationList {

  private List<Location> rows;

  public List<Location> getRows() {
    return rows;
  }

  public void setRows(Location[] rows) {
    this.rows = Arrays.asList(rows);
  }

}
