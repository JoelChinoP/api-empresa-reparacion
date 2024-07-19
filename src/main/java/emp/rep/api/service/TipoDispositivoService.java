package emp.rep.api.service;

import emp.rep.api.dto.TipoDispositivoDTO;
import emp.rep.api.model.Caracteristica;
import emp.rep.api.model.TipoDispositivo;
import emp.rep.api.repository.TipoDispositivoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TipoDispositivoService {

    @Autowired
    private TipoDispositivoRepository repositorio;




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
}
