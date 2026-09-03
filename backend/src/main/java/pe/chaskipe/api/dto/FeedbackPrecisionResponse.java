package pe.chaskipe.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import pe.chaskipe.api.model.FeedbackPrecision;

public record FeedbackPrecisionResponse(
    Long feedbackId,
    Long mensajeId,
    UUID usuarioId,
    Short calificacion,
    String comentario,
    OffsetDateTime fechaHora) {
  public static FeedbackPrecisionResponse from(FeedbackPrecision f) {
    return new FeedbackPrecisionResponse(
        f.getId(),
        f.getMensajeId(),
        f.getUsuarioId(),
        f.getCalificacion(),
        f.getComentario(),
        f.getFechaHora());
  }
}
