package emp.rep.api.service;

import emp.rep.api.model.Fabricante;
import emp.rep.api.repository.FabricanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FabricanteService {

    @Autowired
    private FabricanteRepository repositorio;

    public List<Fabricante> obtenerTodo() {
        return repositorio.findAll();
    }

    public Optional<Fabricante> obtenerPorID(Integer id) {
        return repositorio.findById(id);
    }

    public Fabricante aniadir(Fabricante empresa) {
        return repositorio.save(empresa);
    }

    public Fabricante modificar(Fabricante empresa) {
        return repositorio.save(empresa);
    }

}
