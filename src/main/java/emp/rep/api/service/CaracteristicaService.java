package emp.rep.api.service;

import emp.rep.api.model.Caracteristica;
import emp.rep.api.repository.CaracteristicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CaracteristicaService {

    @Autowired
    private CaracteristicaRepository repositorio;

    public Optional<Caracteristica> obtenerPorID(Integer id) {
        return repositorio.findById(id);
    }

    public Optional<Caracteristica> obtenerPorNombre(String nombre) {
        return repositorio.findByNombreIgnoreCase(nombre);
    }

    public Caracteristica aniadir(Caracteristica objeto){
        return repositorio.save(objeto);
    }

    public Caracteristica modificar(Caracteristica objeto) {
        return repositorio.save(objeto);
    }

    public void eliminar(Caracteristica objeto) {
        repositorio.delete(objeto);
    }

    public List<Caracteristica> obtenerTodo() {
        return repositorio.findAll();
    }
}
