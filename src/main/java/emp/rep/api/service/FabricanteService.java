package emp.rep.api.service;

import emp.rep.api.dto.BasicoDTO;
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

    public Optional<BasicoDTO> obtenerPorID(Integer id) {
        return repositorio.findById(id)
                .map(BasicoDTO::new);
    }

    public Optional<BasicoDTO> obtenerPorNombre(String nombre) {
        return repositorio.findByNombreIgnoreCase(nombre)
                .map(BasicoDTO::new);
    }

    public BasicoDTO aniadir(BasicoDTO obj) {
        return new BasicoDTO(repositorio.save(new Fabricante(
                obj.id(), obj.nombre()
        )));
    }

    public BasicoDTO modificar(BasicoDTO obj) {
        return new BasicoDTO(repositorio.save(new Fabricante(
                obj.id(), obj.nombre()
        )));
    }

    public void eliminar(BasicoDTO obj) {
        repositorio.delete(new Fabricante(
                obj.id(), obj.nombre()
        ));
    }

    public List<BasicoDTO> obtenerTodo() {
        return repositorio.findAll().
                stream().map(BasicoDTO::new)
                .toList();
    }

}
