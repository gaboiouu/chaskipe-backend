package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
  private final UsuarioService service;

  public UsuarioController(UsuarioService s) {
    service = s;
  }

  @GetMapping
  public List<UsuarioResponse> listar() {
    return service.listar();
  }

  @GetMapping("/{id}")
  public UsuarioResponse obtener(@PathVariable UUID id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioResponse crear(@Valid @RequestBody UsuarioRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public UsuarioResponse actualizar(@PathVariable UUID id, @Valid @RequestBody UsuarioRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable UUID id) {
    service.eliminar(id);
  }
}
