package pe.chaskipe.api.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.SecuenciaAvatarRequest;
import pe.chaskipe.api.repository.MensajeRepository;
import pe.chaskipe.api.repository.SecuenciaAvatarRepository;

@ExtendWith(MockitoExtension.class)
class SecuenciaAvatarServiceTest {
  @Mock SecuenciaAvatarRepository repository;
  @Mock MensajeRepository mensajes;

  @Test
  void rechazaGlosaQueNoEsJson() {
    SecuenciaAvatarService service =
        new SecuenciaAvatarService(repository, mensajes, new ObjectMapper());
    assertThrows(
        ResponseStatusException.class,
        () -> service.crear(new SecuenciaAvatarRequest(1L, "no-es-json", 100)));
    verify(repository, never()).save(any());
  }
}
