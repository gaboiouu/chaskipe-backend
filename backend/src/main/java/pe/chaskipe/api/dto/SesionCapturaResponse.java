package pe.chaskipe.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import pe.chaskipe.api.model.SesionCaptura;

public record SesionCapturaResponse(
    Long sesionCapturaId,
    Long conversacionId,
    UUID usuarioId,
    OffsetDateTime fechaHora,
    Integer duracionSegundos,
    String rutaVideo,
    String estadoDeteccion) {
  public static SesionCapturaResponse from(SesionCaptura s) {
    return new SesionCapturaResponse(
        s.getId(),
        s.getConversacionId(),
        s.getUsuarioId(),
        s.getFechaHora(),
        s.getDuracionSegundos(),
        s.getRutaVideo(),
        s.getEstadoDeteccion());
  }
}
