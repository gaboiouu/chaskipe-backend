package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.ConfiguracionService;

@RestController
@RequestMapping("/v1/configuraciones")
public class ConfiguracionController {
  private final ConfiguracionService service;

  public ConfiguracionController(ConfiguracionService s) {
    service = s;
  }

  @GetMapping
  public List<ConfiguracionResponse> listar() {
    return service.listar();
  }

  @GetMapping("/usuario/{usuarioId}")
  public ConfiguracionResponse porUsuario(@PathVariable UUID usuarioId) {
    return service.porUsuario(usuarioId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ConfiguracionResponse crear(@Valid @RequestBody ConfiguracionRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public ConfiguracionResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody ConfiguracionRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
