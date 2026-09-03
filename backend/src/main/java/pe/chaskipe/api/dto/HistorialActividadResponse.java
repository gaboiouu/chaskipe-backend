package pe.chaskipe.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import pe.chaskipe.api.model.HistorialActividad;

public record HistorialActividadResponse(
    Long historialId,
    UUID usuarioId,
    Long conversacionId,
    String icono,
    String titulo,
    String tipo,
    OffsetDateTime fechaHora) {
  public static HistorialActividadResponse from(HistorialActividad h) {
    return new HistorialActividadResponse(
        h.getId(),
        h.getUsuarioId(),
        h.getConversacionId(),
        h.getIcono(),
        h.getTitulo(),
        h.getTipo(),
        h.getFechaHora());
  }
}
