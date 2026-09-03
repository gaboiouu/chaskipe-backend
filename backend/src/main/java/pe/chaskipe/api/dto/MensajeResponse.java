package pe.chaskipe.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import pe.chaskipe.api.model.Mensaje;

public record MensajeResponse(
    Long mensajeId,
    Long conversacionId,
    Long sesionCapturaId,
    String emisor,
    String tipoContenido,
    String contenido,
    BigDecimal precisionTraduccion,
    OffsetDateTime fechaHora) {
  public static MensajeResponse from(Mensaje m) {
    return new MensajeResponse(
        m.getId(),
        m.getConversacionId(),
        m.getSesionCapturaId(),
        m.getEmisor(),
        m.getTipoContenido(),
        m.getContenido(),
        m.getPrecisionTraduccion(),
        m.getFechaHora());
  }
}
