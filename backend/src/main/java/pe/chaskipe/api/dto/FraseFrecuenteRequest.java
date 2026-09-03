package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;

public record FraseFrecuenteRequest(
    @NotBlank String texto, String categoria, @NotNull @PositiveOrZero Integer ordenUso) {}
