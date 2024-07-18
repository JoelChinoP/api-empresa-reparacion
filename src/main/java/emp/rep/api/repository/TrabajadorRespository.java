package emp.rep.api.repository;

import emp.rep.api.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRespository extends JpaRepository<Trabajador, String> {
}
