package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.HistorialActividad;
import pe.chaskipe.api.repository.ConversacionRepository;
import pe.chaskipe.api.repository.HistorialActividadRepository;
import pe.chaskipe.api.repository.UsuarioRepository;

@Service
@Transactional
public class HistorialActividadService {
  private final HistorialActividadRepository repository;
  private final UsuarioRepository usuarios;
  private final ConversacionRepository conversaciones;

  public HistorialActividadService(
      HistorialActividadRepository r, UsuarioRepository u, ConversacionRepository c) {
    repository = r;
    usuarios = u;
    conversaciones = c;
  }

  @Transactional(readOnly = true)
  public List<HistorialActividadResponse> listar(UUID usuarioId) {
    List<HistorialActividad> l =
        usuarioId == null
            ? repository.findAll()
            : repository.findByUsuarioIdOrderByFechaHoraDesc(usuarioId);
    return l.stream().map(HistorialActividadResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public HistorialActividadResponse obtener(Long id) {
    return HistorialActividadResponse.from(buscar(id));
  }

  public HistorialActividadResponse crear(HistorialActividadRequest r) {
    validar(r);
    return HistorialActividadResponse.from(
        repository.save(
            new HistorialActividad(
                r.usuarioId(), r.conversacionId(), r.icono(), r.titulo(), r.tipo())));
  }

  public HistorialActividadResponse actualizar(Long id, HistorialActividadRequest r) {
    HistorialActividad h = buscar(id);
    if (!h.getUsuarioId().equals(r.usuarioId())
        || !Objects.equals(h.getConversacionId(), r.conversacionId()))
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No se puede cambiar el usuario o conversación");
    h.actualizar(r.icono(), r.titulo(), r.tipo());
    return HistorialActividadResponse.from(repository.save(h));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private void validar(HistorialActividadRequest r) {
    if (!usuarios.existsById(r.usuarioId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe");
    if (r.conversacionId() != null) {
      var c =
          conversaciones
              .findById(r.conversacionId())
              .orElseThrow(
                  () ->
                      new ResponseStatusException(
                          HttpStatus.BAD_REQUEST, "La conversación no existe"));
      if (!c.getUsuarioId().equals(r.usuarioId()))
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "La conversación no pertenece al usuario");
    }
  }

  private HistorialActividad buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actividad no encontrada"));
  }
}
