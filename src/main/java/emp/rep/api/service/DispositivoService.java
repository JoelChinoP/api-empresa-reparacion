package emp.rep.api.service;

import emp.rep.api.model.Dispositivo;
import emp.rep.api.repository.DispositivoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DispositivoService {

    @Autowired
    private DispositivoRepository repositorio;

    public List<Dispositivo> obtenerTodo() {
        return repositorio.findAll();
    }

    public Dispositivo aniadir(Dispositivo obj) {
        return repositorio.save(obj);
    }

    public Dispositivo modificar(Dispositivo obj) {
        return repositorio.save(obj);
    }

    public Optional<Dispositivo> obtenerPorID(Long id) {
        return repositorio.findById(id);
    }

    public void eliminar(Dispositivo obj) {
        repositorio.delete(obj);
    }
}
