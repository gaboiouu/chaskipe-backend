package pe.chaskipe.api.dto;

import java.math.BigDecimal;
import java.util.UUID;
import pe.chaskipe.api.model.ConfiguracionUsuario;

public record ConfiguracionResponse(
    Long configuracionId,
    UUID usuarioId,
    String vozSeleccionada,
    BigDecimal velocidadAudio,
    BigDecimal velocidadAvatar,
    boolean subtitulosActivos,
    BigDecimal tamanoFuente,
    String idiomaSenas) {
  public static ConfiguracionResponse from(ConfiguracionUsuario c) {
    return new ConfiguracionResponse(
        c.getId(),
        c.getUsuarioId(),
        c.getVozSeleccionada(),
        c.getVelocidadAudio(),
        c.getVelocidadAvatar(),
        c.isSubtitulosActivos(),
        c.getTamanoFuente(),
        c.getIdiomaSenas());
  }
}
