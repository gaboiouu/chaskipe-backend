package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.SesionCaptura;
import pe.chaskipe.api.repository.ConversacionRepository;
import pe.chaskipe.api.repository.SesionCapturaRepository;

@Service
@Transactional
public class SesionCapturaService {
  private final SesionCapturaRepository repository;
  private final ConversacionRepository conversaciones;

  public SesionCapturaService(SesionCapturaRepository r, ConversacionRepository c) {
    repository = r;
    conversaciones = c;
  }

  @Transactional(readOnly = true)
  public List<SesionCapturaResponse> listar(Long conversacionId) {
    List<SesionCaptura> l =
        conversacionId == null
            ? repository.findAll()
            : repository.findByConversacionIdOrderByFechaHoraAsc(conversacionId);
    return l.stream().map(SesionCapturaResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public SesionCapturaResponse obtener(Long id) {
    return SesionCapturaResponse.from(buscar(id));
  }

  public SesionCapturaResponse crear(SesionCapturaRequest r) {
    validarRelacion(r.conversacionId(), r.usuarioId());
    return SesionCapturaResponse.from(
        repository.save(
            new SesionCaptura(
                r.conversacionId(),
                r.usuarioId(),
                r.duracionSegundos(),
                r.rutaVideo(),
                r.estadoDeteccion())));
  }

  public SesionCapturaResponse actualizar(Long id, SesionCapturaRequest r) {
    SesionCaptura s = buscar(id);
    if (!s.getConversacionId().equals(r.conversacionId())
        || !s.getUsuarioId().equals(r.usuarioId()))
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No se puede cambiar la conversación o el usuario");
    s.actualizar(r.duracionSegundos(), r.rutaVideo(), r.estadoDeteccion());
    return SesionCapturaResponse.from(repository.save(s));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private void validarRelacion(Long c, UUID u) {
    var conversacion =
        conversaciones
            .findById(c)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "La conversación no existe"));
    if (!conversacion.getUsuarioId().equals(u))
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "La conversación no pertenece al usuario");
  }

  private SesionCaptura buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Sesión de captura no encontrada"));
  }
}
