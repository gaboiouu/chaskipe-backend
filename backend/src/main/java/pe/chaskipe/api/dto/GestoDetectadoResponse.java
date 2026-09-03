package pe.chaskipe.api.dto;

import java.math.BigDecimal;
import pe.chaskipe.api.model.GestoDetectado;

public record GestoDetectadoResponse(
    Long gestoDetectadoId,
    Long sesionCapturaId,
    String etiqueta,
    BigDecimal confianzaDeteccion,
    Integer timestampMs) {
  public static GestoDetectadoResponse from(GestoDetectado g) {
    return new GestoDetectadoResponse(
        g.getId(),
        g.getSesionCapturaId(),
        g.getEtiqueta(),
        g.getConfianzaDeteccion(),
        g.getTimestampMs());
  }
}
