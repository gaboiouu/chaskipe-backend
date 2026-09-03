package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;

public record SecuenciaAvatarRequest(
    @NotNull Long mensajeId, @NotBlank String glosaJson, @PositiveOrZero Integer duracionMs) {}
