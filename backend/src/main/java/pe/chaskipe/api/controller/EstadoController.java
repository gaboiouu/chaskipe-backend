package pe.chaskipe.api.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstadoController {

  @GetMapping({"", "/"})
  public Map<String, String> estado() {
    return Map.of("servicio", "Chaskipé API", "estado", "funcionando");
  }
}
