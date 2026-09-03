package pe.chaskipe.api.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.SesionCapturaRequest;
import pe.chaskipe.api.model.Conversacion;
import pe.chaskipe.api.repository.ConversacionRepository;
import pe.chaskipe.api.repository.SesionCapturaRepository;

@ExtendWith(MockitoExtension.class)
class SesionCapturaServiceTest {
  @Mock SesionCapturaRepository repository;
  @Mock ConversacionRepository conversaciones;

  @Test
  void rechazaCapturaSiLaConversacionEsDeOtroUsuario() {
    UUID propietario = UUID.randomUUID();
    UUID solicitante = UUID.randomUUID();
    when(conversaciones.findById(1L))
        .thenReturn(Optional.of(new Conversacion(propietario, "Prueba")));
    SesionCapturaService service = new SesionCapturaService(repository, conversaciones);
    var request = new SesionCapturaRequest(1L, solicitante, 10, null, "procesando");
    assertThrows(ResponseStatusException.class, () -> service.crear(request));
    verify(repository, never()).save(any());
  }
}
