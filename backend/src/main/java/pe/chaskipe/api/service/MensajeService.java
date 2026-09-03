package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.Mensaje;
import pe.chaskipe.api.repository.ConversacionRepository;
import pe.chaskipe.api.repository.MensajeRepository;
import pe.chaskipe.api.repository.SesionCapturaRepository;

@Service
@Transactional
public class MensajeService {
  private final MensajeRepository repository;
  private final ConversacionRepository conversaciones;
  private final SesionCapturaRepository sesiones;

  public MensajeService(MensajeRepository r, ConversacionRepository c, SesionCapturaRepository s) {
    repository = r;
    conversaciones = c;
    sesiones = s;
  }

  @Transactional(readOnly = true)
  public List<MensajeResponse> listar(Long conversacionId) {
    List<Mensaje> l =
        conversacionId == null
            ? repository.findAll()
            : repository.findByConversacionIdOrderByFechaHoraAsc(conversacionId);
    return l.stream().map(MensajeResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public MensajeResponse obtener(Long id) {
    return MensajeResponse.from(buscar(id));
  }

  public MensajeResponse crear(MensajeRequest r) {
    validar(r);
    return MensajeResponse.from(
        repository.save(
            new Mensaje(
                r.conversacionId(),
                r.sesionCapturaId(),
                r.emisor(),
                r.tipoContenido(),
                r.contenido(),
                r.precisionTraduccion())));
  }

  public MensajeResponse actualizar(Long id, MensajeRequest r) {
    Mensaje m = buscar(id);
    if (!m.getConversacionId().equals(r.conversacionId())
        || !Objects.equals(m.getSesionCapturaId(), r.sesionCapturaId()))
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No se puede cambiar el origen del mensaje");
    m.actualizar(r.emisor(), r.tipoContenido(), r.contenido(), r.precisionTraduccion());
    return MensajeResponse.from(repository.save(m));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private void validar(MensajeRequest r) {
    if (!conversaciones.existsById(r.conversacionId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La conversación no existe");
    if (r.sesionCapturaId() != null) {
      var s =
          sesiones
              .findById(r.sesionCapturaId())
              .orElseThrow(
                  () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "La sesión no existe"));
      if (!s.getConversacionId().equals(r.conversacionId()))
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "La sesión no pertenece a la conversación");
    }
  }

  private Mensaje buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensaje no encontrado"));
  }
}
