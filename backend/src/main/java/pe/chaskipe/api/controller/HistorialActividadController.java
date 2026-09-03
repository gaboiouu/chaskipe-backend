package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.HistorialActividadService;

@RestController
@RequestMapping("/v1/historial")
public class HistorialActividadController {
  private final HistorialActividadService service;

  public HistorialActividadController(HistorialActividadService s) {
    service = s;
  }

  @GetMapping
  public List<HistorialActividadResponse> listar(@RequestParam(required = false) UUID usuarioId) {
    return service.listar(usuarioId);
  }

  @GetMapping("/{id}")
  public HistorialActividadResponse obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public HistorialActividadResponse crear(@Valid @RequestBody HistorialActividadRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public HistorialActividadResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody HistorialActividadRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
