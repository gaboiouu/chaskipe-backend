package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.ConfiguracionUsuario;
import pe.chaskipe.api.repository.ConfiguracionRepository;
import pe.chaskipe.api.repository.UsuarioRepository;

@Service
@Transactional
public class ConfiguracionService {
  private final ConfiguracionRepository repository;
  private final UsuarioRepository usuarios;

  public ConfiguracionService(ConfiguracionRepository r, UsuarioRepository u) {
    repository = r;
    usuarios = u;
  }

  @Transactional(readOnly = true)
  public List<ConfiguracionResponse> listar() {
    return repository.findAll().stream().map(ConfiguracionResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public ConfiguracionResponse porUsuario(UUID id) {
    return ConfiguracionResponse.from(
        repository
            .findByUsuarioId(id)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Configuración no encontrada")));
  }

  public ConfiguracionResponse crear(ConfiguracionRequest r) {
    if (!usuarios.existsById(r.usuarioId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe");
    if (repository.existsByUsuarioId(r.usuarioId()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya tiene configuración");
    ConfiguracionUsuario c = new ConfiguracionUsuario(r.usuarioId());
    aplicar(c, r);
    return ConfiguracionResponse.from(repository.save(c));
  }

  public ConfiguracionResponse actualizar(Long id, ConfiguracionRequest r) {
    ConfiguracionUsuario c = buscar(id);
    if (!c.getUsuarioId().equals(r.usuarioId()))
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No se puede cambiar el usuario de la configuración");
    aplicar(c, r);
    return ConfiguracionResponse.from(repository.save(c));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private ConfiguracionUsuario buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuración no encontrada"));
  }

  private void aplicar(ConfiguracionUsuario c, ConfiguracionRequest r) {
    c.actualizar(
        r.vozSeleccionada(),
        r.velocidadAudio(),
        r.velocidadAvatar(),
        r.subtitulosActivos(),
        r.tamanoFuente(),
        r.idiomaSenas());
  }
}
