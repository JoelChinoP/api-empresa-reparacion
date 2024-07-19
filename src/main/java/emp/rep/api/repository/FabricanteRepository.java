package emp.rep.api.repository;

import emp.rep.api.model.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {
    Optional<Fabricante> findByNombreIgnoreCase(String nombre);
}
