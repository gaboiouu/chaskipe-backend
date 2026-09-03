package pe.chaskipe.api.dto;

import java.math.BigDecimal;
import pe.chaskipe.api.model.AudioMensaje;

public record AudioMensajeResponse(
    Long audioMensajeId,
    Long mensajeId,
    String rutaAudio,
    String vozUsada,
    BigDecimal velocidad,
    Integer duracionSegundos) {
  public static AudioMensajeResponse from(AudioMensaje a) {
    return new AudioMensajeResponse(
        a.getId(),
        a.getMensajeId(),
        a.getRutaAudio(),
        a.getVozUsada(),
        a.getVelocidad(),
        a.getDuracionSegundos());
  }
}
