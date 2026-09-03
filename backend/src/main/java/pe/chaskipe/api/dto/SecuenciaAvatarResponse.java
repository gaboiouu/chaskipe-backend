package pe.chaskipe.api.dto;

import pe.chaskipe.api.model.SecuenciaSenasAvatar;

public record SecuenciaAvatarResponse(
    Long secuenciaSenasId, Long mensajeId, String glosaJson, Integer duracionMs) {
  public static SecuenciaAvatarResponse from(SecuenciaSenasAvatar s) {
    return new SecuenciaAvatarResponse(
        s.getId(), s.getMensajeId(), s.getGlosaJson(), s.getDuracionMs());
  }
}
