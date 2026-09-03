package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.FraseFrecuente;

public interface FraseFrecuenteRepository extends JpaRepository<FraseFrecuente, Long> {
  List<FraseFrecuente> findAllByOrderByOrdenUsoAsc();

  List<FraseFrecuente> findByCategoriaOrderByOrdenUsoAsc(String categoria);
}
