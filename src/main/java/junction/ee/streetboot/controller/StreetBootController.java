package junction.ee.streetboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StreetBootController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/street")
  public String street() { return "street"; }
}
