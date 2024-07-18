package emp.rep.api.repository;

import emp.rep.api.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {
    Optional<Caracteristica> findByNombreIgnoreCase(String nombre);
}
