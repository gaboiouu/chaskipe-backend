package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.FeedbackPrecision;
import pe.chaskipe.api.repository.FeedbackPrecisionRepository;
import pe.chaskipe.api.repository.MensajeRepository;
import pe.chaskipe.api.repository.UsuarioRepository;

@Service
@Transactional
public class FeedbackPrecisionService {
  private final FeedbackPrecisionRepository repository;
  private final MensajeRepository mensajes;
  private final UsuarioRepository usuarios;

  public FeedbackPrecisionService(
      FeedbackPrecisionRepository r, MensajeRepository m, UsuarioRepository u) {
    repository = r;
    mensajes = m;
    usuarios = u;
  }

  @Transactional(readOnly = true)
  public List<FeedbackPrecisionResponse> listar(Long mensajeId, UUID usuarioId) {
    List<FeedbackPrecision> l =
        mensajeId != null
            ? repository.findByMensajeId(mensajeId)
            : usuarioId != null
                ? repository.findByUsuarioIdOrderByFechaHoraDesc(usuarioId)
                : repository.findAll();
    return l.stream().map(FeedbackPrecisionResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public FeedbackPrecisionResponse obtener(Long id) {
    return FeedbackPrecisionResponse.from(buscar(id));
  }

  public FeedbackPrecisionResponse crear(FeedbackPrecisionRequest r) {
    if (!mensajes.existsById(r.mensajeId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El mensaje no existe");
    if (!usuarios.existsById(r.usuarioId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe");
    return FeedbackPrecisionResponse.from(
        repository.save(
            new FeedbackPrecision(r.mensajeId(), r.usuarioId(), r.calificacion(), r.comentario())));
  }

  public FeedbackPrecisionResponse actualizar(Long id, FeedbackPrecisionRequest r) {
    FeedbackPrecision f = buscar(id);
    if (!f.getMensajeId().equals(r.mensajeId()) || !f.getUsuarioId().equals(r.usuarioId()))
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No se puede cambiar el mensaje o usuario");
    f.actualizar(r.calificacion(), r.comentario());
    return FeedbackPrecisionResponse.from(repository.save(f));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private FeedbackPrecision buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback no encontrado"));
  }
}
