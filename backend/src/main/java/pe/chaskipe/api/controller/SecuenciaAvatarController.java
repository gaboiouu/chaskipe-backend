package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.SecuenciaAvatarService;

@RestController
@RequestMapping("/api/v1/secuencias-avatar")
public class SecuenciaAvatarController {
  private final SecuenciaAvatarService service;

  public SecuenciaAvatarController(SecuenciaAvatarService s) {
    service = s;
  }

  @GetMapping
  public List<SecuenciaAvatarResponse> listar() {
    return service.listar();
  }

  @GetMapping("/mensaje/{mensajeId}")
  public SecuenciaAvatarResponse porMensaje(@PathVariable Long mensajeId) {
    return service.porMensaje(mensajeId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SecuenciaAvatarResponse crear(@Valid @RequestBody SecuenciaAvatarRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public SecuenciaAvatarResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody SecuenciaAvatarRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
