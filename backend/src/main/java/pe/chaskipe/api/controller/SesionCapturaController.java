package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.SesionCapturaService;

@RestController
@RequestMapping("/api/v1/sesiones-captura")
public class SesionCapturaController {
  private final SesionCapturaService service;

  public SesionCapturaController(SesionCapturaService s) {
    service = s;
  }

  @GetMapping
  public List<SesionCapturaResponse> listar(@RequestParam(required = false) Long conversacionId) {
    return service.listar(conversacionId);
  }

  @GetMapping("/{id}")
  public SesionCapturaResponse obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SesionCapturaResponse crear(@Valid @RequestBody SesionCapturaRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public SesionCapturaResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody SesionCapturaRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
