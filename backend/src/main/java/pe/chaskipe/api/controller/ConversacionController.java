package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.ConversacionService;

@RestController
@RequestMapping("/api/v1/conversaciones")
public class ConversacionController {
  private final ConversacionService service;

  public ConversacionController(ConversacionService s) {
    service = s;
  }

  @GetMapping
  public List<ConversacionResponse> listar(@RequestParam(required = false) UUID usuarioId) {
    return service.listar(usuarioId);
  }

  @GetMapping("/{id}")
  public ConversacionResponse obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ConversacionResponse crear(@Valid @RequestBody ConversacionRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public ConversacionResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody ConversacionRequest r) {
    return service.actualizar(id, r);
  }

  @PatchMapping("/{id}/finalizar")
  public ConversacionResponse finalizar(@PathVariable Long id) {
    return service.finalizar(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
