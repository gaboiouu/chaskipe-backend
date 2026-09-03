package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record FeedbackPrecisionRequest(
    @NotNull Long mensajeId,
    @NotNull UUID usuarioId,
    @NotNull @Min(1) @Max(5) Short calificacion,
    String comentario) {}
