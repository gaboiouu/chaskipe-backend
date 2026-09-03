package pe.chaskipe.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.SecuenciaSenasAvatar;
import pe.chaskipe.api.repository.MensajeRepository;
import pe.chaskipe.api.repository.SecuenciaAvatarRepository;

@Service
@Transactional
public class SecuenciaAvatarService {
  private final SecuenciaAvatarRepository repository;
  private final MensajeRepository mensajes;
  private final ObjectMapper mapper;

  public SecuenciaAvatarService(SecuenciaAvatarRepository r, MensajeRepository m, ObjectMapper o) {
    repository = r;
    mensajes = m;
    mapper = o;
  }

  @Transactional(readOnly = true)
  public List<SecuenciaAvatarResponse> listar() {
    return repository.findAll().stream().map(SecuenciaAvatarResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public SecuenciaAvatarResponse porMensaje(Long id) {
    return SecuenciaAvatarResponse.from(
        repository
            .findByMensajeId(id)
            .orElseThrow(
                () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Secuencia no encontrada")));
  }

  public SecuenciaAvatarResponse crear(SecuenciaAvatarRequest r) {
    validarJson(r.glosaJson());
    if (!mensajes.existsById(r.mensajeId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El mensaje no existe");
    if (repository.existsByMensajeId(r.mensajeId()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "El mensaje ya tiene secuencia");
    return SecuenciaAvatarResponse.from(
        repository.save(new SecuenciaSenasAvatar(r.mensajeId(), r.glosaJson(), r.duracionMs())));
  }

  public SecuenciaAvatarResponse actualizar(Long id, SecuenciaAvatarRequest r) {
    validarJson(r.glosaJson());
    SecuenciaSenasAvatar s = buscar(id);
    if (!s.getMensajeId().equals(r.mensajeId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede cambiar el mensaje");
    s.actualizar(r.glosaJson(), r.duracionMs());
    return SecuenciaAvatarResponse.from(repository.save(s));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private void validarJson(String json) {
    try {
      mapper.readTree(json);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "glosaJson no contiene JSON válido");
    }
  }

  private SecuenciaSenasAvatar buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Secuencia no encontrada"));
  }
}
