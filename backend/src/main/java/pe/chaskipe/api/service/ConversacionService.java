package pe.chaskipe.api.service;

import java.time.OffsetDateTime;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.Conversacion;
import pe.chaskipe.api.repository.ConversacionRepository;
import pe.chaskipe.api.repository.UsuarioRepository;

@Service
@Transactional
public class ConversacionService {
  private final ConversacionRepository repository;
  private final UsuarioRepository usuarios;

  public ConversacionService(ConversacionRepository r, UsuarioRepository u) {
    repository = r;
    usuarios = u;
  }

  @Transactional(readOnly = true)
  public List<ConversacionResponse> listar(UUID usuarioId) {
    List<Conversacion> l =
        usuarioId == null
            ? repository.findAll()
            : repository.findByUsuarioIdOrderByFechaInicioDesc(usuarioId);
    return l.stream().map(ConversacionResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public ConversacionResponse obtener(Long id) {
    return ConversacionResponse.from(buscar(id));
  }

  public ConversacionResponse crear(ConversacionRequest r) {
    if (!usuarios.existsById(r.usuarioId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe");
    return ConversacionResponse.from(repository.save(new Conversacion(r.usuarioId(), r.titulo())));
  }

  public ConversacionResponse actualizar(Long id, ConversacionRequest r) {
    Conversacion c = buscar(id);
    if (!c.getUsuarioId().equals(r.usuarioId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede cambiar el usuario");
    c.actualizar(r.titulo(), r.fechaFin());
    return ConversacionResponse.from(repository.save(c));
  }

  public ConversacionResponse finalizar(Long id) {
    Conversacion c = buscar(id);
    c.actualizar(c.getTitulo(), OffsetDateTime.now());
    return ConversacionResponse.from(repository.save(c));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private Conversacion buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversación no encontrada"));
  }
}
