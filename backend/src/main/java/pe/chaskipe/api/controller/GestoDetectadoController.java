package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.GestoDetectadoService;

@RestController
@RequestMapping("/api/v1/gestos")
public class GestoDetectadoController {
  private final GestoDetectadoService service;

  public GestoDetectadoController(GestoDetectadoService s) {
    service = s;
  }

  @GetMapping
  public List<GestoDetectadoResponse> listar(@RequestParam(required = false) Long sesionCapturaId) {
    return service.listar(sesionCapturaId);
  }

  @GetMapping("/{id}")
  public GestoDetectadoResponse obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GestoDetectadoResponse crear(@Valid @RequestBody GestoDetectadoRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public GestoDetectadoResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody GestoDetectadoRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
