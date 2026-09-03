package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.MensajeService;

@RestController
@RequestMapping("/api/v1/mensajes")
public class MensajeController {
  private final MensajeService service;

  public MensajeController(MensajeService s) {
    service = s;
  }

  @GetMapping
  public List<MensajeResponse> listar(@RequestParam(required = false) Long conversacionId) {
    return service.listar(conversacionId);
  }

  @GetMapping("/{id}")
  public MensajeResponse obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MensajeResponse crear(@Valid @RequestBody MensajeRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public MensajeResponse actualizar(@PathVariable Long id, @Valid @RequestBody MensajeRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
