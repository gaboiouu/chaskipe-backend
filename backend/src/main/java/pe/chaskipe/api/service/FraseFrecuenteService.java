package pe.chaskipe.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.chaskipe.api.dto.*;
import pe.chaskipe.api.model.FraseFrecuente;
import pe.chaskipe.api.repository.FraseFrecuenteRepository;

@Service
@Transactional
public class FraseFrecuenteService {
  private final FraseFrecuenteRepository repository;

  public FraseFrecuenteService(FraseFrecuenteRepository r) {
    repository = r;
  }

  @Transactional(readOnly = true)
  public List<FraseFrecuenteResponse> listar(String categoria) {
    List<FraseFrecuente> l =
        categoria == null
            ? repository.findAllByOrderByOrdenUsoAsc()
            : repository.findByCategoriaOrderByOrdenUsoAsc(categoria);
    return l.stream().map(FraseFrecuenteResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public FraseFrecuenteResponse obtener(Long id) {
    return FraseFrecuenteResponse.from(buscar(id));
  }

  public FraseFrecuenteResponse crear(FraseFrecuenteRequest r) {
    return FraseFrecuenteResponse.from(
        repository.save(new FraseFrecuente(r.texto(), r.categoria(), r.ordenUso())));
  }

  public FraseFrecuenteResponse actualizar(Long id, FraseFrecuenteRequest r) {
    FraseFrecuente f = buscar(id);
    f.actualizar(r.texto(), r.categoria(), r.ordenUso());
    return FraseFrecuenteResponse.from(repository.save(f));
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }

  private FraseFrecuente buscar(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase no encontrada"));
  }
}
