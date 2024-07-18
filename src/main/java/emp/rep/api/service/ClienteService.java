package emp.rep.api.service;

import emp.rep.api.model.Cliente;
import emp.rep.api.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;

    public List<Cliente> obtenerTodo() {
        return repositorio.findAll();
    }

    public Cliente aniadir(Cliente obj) {
        return repositorio.save(obj);
    }

    public Cliente modificar(Cliente obj) {
        return repositorio.save(obj);
    }

    public Optional<Cliente> obtenerPorDNI(String dni) {
        return repositorio.findById(dni);
    }

    public void eliminar(Cliente obj) {
        repositorio.delete(obj);
    }
}
