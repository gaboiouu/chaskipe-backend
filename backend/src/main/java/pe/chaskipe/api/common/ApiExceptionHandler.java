package pe.chaskipe.api.common;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ProblemDetail validation(MethodArgumentNotValidException exception) {
    ProblemDetail detail =
        ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST, "La solicitud contiene datos inválidos.");
    detail.setType(URI.create("https://chaskipe.pe/errors/validation"));
    Map<String, String> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .collect(
                Collectors.toMap(
                    e -> e.getField(),
                    e -> e.getDefaultMessage() == null ? "valor inválido" : e.getDefaultMessage(),
                    (a, b) -> a));
    detail.setProperty("errors", errors);
    return detail;
  }

  @ExceptionHandler(ResponseStatusException.class)
  ProblemDetail responseStatus(ResponseStatusException exception) {
    return ProblemDetail.forStatusAndDetail(
        exception.getStatusCode(),
        exception.getReason() == null ? "Error en la solicitud" : exception.getReason());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  ProblemDetail dataIntegrity(DataIntegrityViolationException exception) {
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.CONFLICT,
        "La operación viola una relación, restricción o valor único de la base de datos.");
  }
}
