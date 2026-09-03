package pe.chaskipe.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.UsuarioRequest;
import pe.chaskipe.api.model.Usuario;
import pe.chaskipe.api.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
  @Mock UsuarioRepository repository;

  @Test
  void creaPerfilCuandoLosDatosSonValidos() {
    UsuarioService service = new UsuarioService(repository);
    UUID id = UUID.randomUUID();
    when(repository.save(any(Usuario.class))).thenAnswer(i -> i.getArgument(0));
    var response =
        service.crear(
            new UsuarioRequest(id, "Ana", "Quispe", "ana@example.com", "ambos", null, true));
    assertEquals(id, response.usuarioId());
    verify(repository).save(any(Usuario.class));
  }

  @Test
  void rechazaPerfilDuplicado() {
    UsuarioService service = new UsuarioService(repository);
    UUID id = UUID.randomUUID();
    when(repository.existsById(id)).thenReturn(true);
    assertThrows(
        ResponseStatusException.class,
        () ->
            service.crear(
                new UsuarioRequest(id, "Ana", "Quispe", "ana@example.com", "oyente", null, true)));
    verify(repository, never()).save(any());
  }
}
