package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.AudioMensaje;
import pe.chaskipe.api.repository.AudioMensajeRepository;
import pe.chaskipe.api.repository.MensajeRepository;

@Service
@Transactional
public class AudioMensajeService {
  private final AudioMensajeRepository repository;
  private final MensajeRepository mensajes;

  public AudioMensajeService(AudioMensajeRepository r, MensajeRepository m) {
    repository = r;
    mensajes = m;
  }

  @Transactional(readOnly = true)
  public List<AudioMensajeResponse> listar() {
    return repository.findAll().stream().map(AudioMensajeResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public AudioMensajeResponse porMensaje(Long id) {
    return AudioMensajeResponse.from(
        repository
            .findByMensajeId(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Audio no encontrado")));
  }

  public AudioMensajeResponse crear(AudioMensajeRequest r) {
    if (!mensajes.existsById(r.mensajeId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El mensaje no existe");
    if (repository.existsByMensajeId(r.mensajeId()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "El mensaje ya tiene audio");
    return AudioMensajeResponse.from(
        repository.save(
            new AudioMensaje(
                r.mensajeId(), r.rutaAudio(), r.vozUsada(), r.velocidad(), r.duracionSegundos())));
  }

  public AudioMensajeResponse actualizar(Long id, AudioMensajeRequest r) {
    AudioMensaje a = buscar(id);
    if (!a.getMensajeId().equals(r.mensajeId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede cambiar el mensaje");
    a.actualizar(r.rutaAudio(), r.vozUsada(), r.velocidad(), r.duracionSegundos());
    return AudioMensajeResponse.from(repository.save(a));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private AudioMensaje buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Audio no encontrado"));
  }
}
