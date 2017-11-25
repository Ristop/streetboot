package junction.ee.streetboot.model.refuel;

import java.util.Arrays;
import java.util.List;

public class RefuelList {

  private List<Refuel> rows;

  public List<Refuel> getRows() {
    return rows;
  }

  public void setRows(Refuel[] rows) {
    this.rows = Arrays.asList(rows);
  }

}
