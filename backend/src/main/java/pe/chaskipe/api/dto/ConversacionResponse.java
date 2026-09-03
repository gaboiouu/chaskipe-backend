package pe.chaskipe.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import pe.chaskipe.api.model.Conversacion;

public record ConversacionResponse(
    Long conversacionId,
    UUID usuarioId,
    String titulo,
    OffsetDateTime fechaInicio,
    OffsetDateTime fechaFin) {
  public static ConversacionResponse from(Conversacion c) {
    return new ConversacionResponse(
        c.getId(), c.getUsuarioId(), c.getTitulo(), c.getFechaInicio(), c.getFechaFin());
  }
}
