package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record MensajeRequest(
    @NotNull Long conversacionId,
    Long sesionCapturaId,
    @NotBlank @Pattern(regexp = "usuario|avatar|interlocutor") String emisor,
    @NotBlank @Pattern(regexp = "texto|audio|senas") String tipoContenido,
    @NotBlank String contenido,
    @DecimalMin("0.00") @DecimalMax("100.00") BigDecimal precisionTraduccion) {}
