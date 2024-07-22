package emp.rep.api.repository;

import emp.rep.api.model.TipoDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoDispositivoRepository extends JpaRepository<TipoDispositivo, Integer> {
    Optional<TipoDispositivo> findByNombreIgnoreCase(String nombre);
}
