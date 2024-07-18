package emp.rep.api.service;

import emp.rep.api.model.Trabajador;
import emp.rep.api.repository.TrabajadorRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrabajadorService {

    @Autowired
    private TrabajadorRespository repositorio;

    public List<Trabajador> obtenerTodo() {
        return repositorio.findAll();
    }

    public Trabajador aniadir(Trabajador obj) {
        return repositorio.save(obj);
    }

    public Trabajador modificar(Trabajador obj) {
        return repositorio.save(obj);
    }

    public Optional<Trabajador> obtenerPorDNI(String dni) {
        return repositorio.findById(dni);
    }

    public void eliminar(Trabajador obj) {
        repositorio.delete(obj);
    }
}
