package junction.ee.streetboot.model.wash;

import java.util.Arrays;
import java.util.List;

public class WashList {

  private List<Wash> rows;

  public List<Wash> getRows() {
    return rows;
  }

  public void setRows(Wash[] rows) {
    this.rows = Arrays.asList(rows);
  }
}
