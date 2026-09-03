package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record AudioMensajeRequest(
    @NotNull Long mensajeId,
    @NotBlank String rutaAudio,
    @NotBlank String vozUsada,
    @NotNull @DecimalMin("0.25") @DecimalMax("3.00") BigDecimal velocidad,
    @PositiveOrZero Integer duracionSegundos) {}
