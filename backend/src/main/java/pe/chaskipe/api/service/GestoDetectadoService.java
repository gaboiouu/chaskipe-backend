package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.GestoDetectado;
import pe.chaskipe.api.repository.GestoDetectadoRepository;
import pe.chaskipe.api.repository.SesionCapturaRepository;

@Service
@Transactional
public class GestoDetectadoService {
  private final GestoDetectadoRepository repository;
  private final SesionCapturaRepository sesiones;

  public GestoDetectadoService(GestoDetectadoRepository r, SesionCapturaRepository s) {
    repository = r;
    sesiones = s;
  }

  @Transactional(readOnly = true)
  public List<GestoDetectadoResponse> listar(Long sesionId) {
    List<GestoDetectado> l =
        sesionId == null
            ? repository.findAll()
            : repository.findBySesionCapturaIdOrderByTimestampMsAsc(sesionId);
    return l.stream().map(GestoDetectadoResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public GestoDetectadoResponse obtener(Long id) {
    return GestoDetectadoResponse.from(buscar(id));
  }

  public GestoDetectadoResponse crear(GestoDetectadoRequest r) {
    if (!sesiones.existsById(r.sesionCapturaId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La sesión de captura no existe");
    return GestoDetectadoResponse.from(
        repository.save(
            new GestoDetectado(
                r.sesionCapturaId(), r.etiqueta(), r.confianzaDeteccion(), r.timestampMs())));
  }

  public GestoDetectadoResponse actualizar(Long id, GestoDetectadoRequest r) {
    GestoDetectado g = buscar(id);
    if (!g.getSesionCapturaId().equals(r.sesionCapturaId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede cambiar la sesión");
    g.actualizar(r.etiqueta(), r.confianzaDeteccion(), r.timestampMs());
    return GestoDetectadoResponse.from(repository.save(g));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private GestoDetectado buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gesto no encontrado"));
  }
}
