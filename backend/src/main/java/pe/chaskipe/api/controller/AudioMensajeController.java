package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.AudioMensajeService;

@RestController
@RequestMapping("/v1/audios")
public class AudioMensajeController {
  private final AudioMensajeService service;

  public AudioMensajeController(AudioMensajeService s) {
    service = s;
  }

  @GetMapping
  public List<AudioMensajeResponse> listar() {
    return service.listar();
  }

  @GetMapping("/mensaje/{mensajeId}")
  public AudioMensajeResponse porMensaje(@PathVariable Long mensajeId) {
    return service.porMensaje(mensajeId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AudioMensajeResponse crear(@Valid @RequestBody AudioMensajeRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public AudioMensajeResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody AudioMensajeRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
