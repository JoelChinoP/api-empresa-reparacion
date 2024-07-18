package emp.rep.api.service;

import emp.rep.api.model.Empresa;
import emp.rep.api.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpresaService {

    @Autowired
    private EmpresaRepository repositorio;


    public List<Empresa> obtenerTodo() {
        return repositorio.findAll();
    }

    public Optional<Empresa> obtenerPorID(String id) {
        return repositorio.findById(id);
    }

    public Empresa aniadir(Empresa empresa) {
        return repositorio.save(empresa);
    }

    public Empresa modificar(Empresa empresa) {
        return repositorio.save(empresa);
    }
}
