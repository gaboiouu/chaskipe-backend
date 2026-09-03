package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.FraseFrecuenteService;

@RestController
@RequestMapping("/v1/frases-frecuentes")
public class FraseFrecuenteController {
  private final FraseFrecuenteService service;

  public FraseFrecuenteController(FraseFrecuenteService s) {
    service = s;
  }

  @GetMapping
  public List<FraseFrecuenteResponse> listar(@RequestParam(required = false) String categoria) {
    return service.listar(categoria);
  }

  @GetMapping("/{id}")
  public FraseFrecuenteResponse obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FraseFrecuenteResponse crear(@Valid @RequestBody FraseFrecuenteRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public FraseFrecuenteResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody FraseFrecuenteRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
