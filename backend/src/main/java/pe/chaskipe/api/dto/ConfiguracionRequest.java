package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public record ConfiguracionRequest(
    @NotNull UUID usuarioId,
    @NotBlank String vozSeleccionada,
    @NotNull @DecimalMin("0.25") @DecimalMax("3.00") BigDecimal velocidadAudio,
    @NotNull @DecimalMin("0.25") @DecimalMax("3.00") BigDecimal velocidadAvatar,
    boolean subtitulosActivos,
    @NotNull @DecimalMin("0.50") @DecimalMax("3.00") BigDecimal tamanoFuente,
    @NotBlank String idiomaSenas) {}
