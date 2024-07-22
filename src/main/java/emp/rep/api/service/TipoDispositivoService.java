package emp.rep.api.service;

import emp.rep.api.dto.BasicoDTO;
import emp.rep.api.dto.TipoDispositivoDTO;
import emp.rep.api.model.Caracteristica;
import emp.rep.api.model.TipoDispositivo;
import emp.rep.api.repository.TipoDispositivoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TipoDispositivoService {

    @Autowired
    private TipoDispositivoRepository repositorio;

    public BasicoDTO aniadir(BasicoDTO obj) {
        TipoDispositivo nuevo = new TipoDispositivo(obj.nombre());
        return new BasicoDTO(repositorio.save(nuevo));
    }

    public Optional<TipoDispositivoDTO> obtenerPorID(Integer id) {
        return repositorio.findById(id)
                .map(this::pasarDatos);
    }

    public Optional<TipoDispositivoDTO> obtenerPorNombre(String nombre) {
        return repositorio.findByNombreIgnoreCase(
                nombre.replace("+", " "))
                .map(this::pasarDatos);
    }


    public List<TipoDispositivoDTO> obtenerTodo() {
        return repositorio.findAll().stream()
                .map(this::pasarDatos)
                .toList();
    }


    // Metodos de ayuda

    public TipoDispositivoDTO pasarDatos(TipoDispositivo obj) {
        return new TipoDispositivoDTO(
                obj.getId(),
                obj.getNombre(),
                obj.getCaracteristicas().stream()
                        .map(Caracteristica::getNombre)
                        .collect(Collectors.toList())
        );
    }


    public List<String> obtenerCaracteristicasPorID(Integer id) {
        return repositorio.findById(id)
                .map(this::pasarDatos)
                .map(TipoDispositivoDTO::caracteristicas)
                .orElse(Collections.emptyList());
    }

    public List<String> obtenerCaracteristicasPorNombre(String nombre) {
        return repositorio.findByNombreIgnoreCase(nombre.replace("+", " "))
                .map(this::pasarDatos)
                .map(TipoDispositivoDTO::caracteristicas)
                .orElse(Collections.emptyList());
    }
}
