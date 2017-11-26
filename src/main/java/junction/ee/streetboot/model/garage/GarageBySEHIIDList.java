package junction.ee.streetboot.model.garage;

import java.util.Arrays;
import java.util.List;

public class GarageBySEHIIDList {

  private List<GarageBySEHIID> rows;

  public List<GarageBySEHIID> getRows() {
    return rows;
  }

  public void setRows(GarageBySEHIID[] rows) {
    this.rows = Arrays.asList(rows);
  }
}
