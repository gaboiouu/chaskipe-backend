package pe.chaskipe.api.controller;

import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.service.FeedbackPrecisionService;

@RestController
@RequestMapping("/v1/feedback")
public class FeedbackPrecisionController {
  private final FeedbackPrecisionService service;

  public FeedbackPrecisionController(FeedbackPrecisionService s) {
    service = s;
  }

  @GetMapping
  public List<FeedbackPrecisionResponse> listar(
      @RequestParam(required = false) Long mensajeId,
      @RequestParam(required = false) UUID usuarioId) {
    return service.listar(mensajeId, usuarioId);
  }

  @GetMapping("/{id}")
  public FeedbackPrecisionResponse obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FeedbackPrecisionResponse crear(@Valid @RequestBody FeedbackPrecisionRequest r) {
    return service.crear(r);
  }

  @PutMapping("/{id}")
  public FeedbackPrecisionResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody FeedbackPrecisionRequest r) {
    return service.actualizar(id, r);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
