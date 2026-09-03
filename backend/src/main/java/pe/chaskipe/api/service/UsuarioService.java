package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.Usuario;
import pe.chaskipe.api.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
  private final UsuarioRepository repository;

  public UsuarioService(UsuarioRepository r) {
    repository = r;
  }

  @Transactional(readOnly = true)
  public List<UsuarioResponse> listar() {
    return repository.findAll().stream().map(UsuarioResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public UsuarioResponse obtener(UUID id) {
    return UsuarioResponse.from(buscar(id));
  }

  public UsuarioResponse crear(UsuarioRequest r) {
    if (repository.existsById(r.usuarioId()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
    if (repository.existsByCorreo(r.correo()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está registrado");
    return UsuarioResponse.from(
        repository.save(
            new Usuario(
                r.usuarioId(),
                r.nombres(),
                r.apellidos(),
                r.correo(),
                r.tipoUsuario(),
                r.fotoPerfilUrl())));
  }

  public UsuarioResponse actualizar(UUID id, UsuarioRequest r) {
    Usuario u = buscar(id);
    u.actualizar(
        r.nombres(),
        r.apellidos(),
        r.correo(),
        r.tipoUsuario(),
        r.fotoPerfilUrl(),
        r.activo() == null || r.activo());
    return UsuarioResponse.from(repository.save(u));
  }

  public void eliminar(UUID id) {
    repository.delete(buscar(id));
  }

  private Usuario buscar(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
  }
}
