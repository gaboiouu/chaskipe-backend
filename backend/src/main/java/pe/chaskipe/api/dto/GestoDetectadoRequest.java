package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record GestoDetectadoRequest(
    @NotNull Long sesionCapturaId,
    @NotBlank String etiqueta,
    @NotNull @DecimalMin("0.00") @DecimalMax("100.00") BigDecimal confianzaDeteccion,
    @NotNull @PositiveOrZero Integer timestampMs) {}
