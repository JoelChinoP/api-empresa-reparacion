package emp.rep.api.service;

import emp.rep.api.dto.DispositivoDTO;
import emp.rep.api.model.Dispositivo;
import emp.rep.api.model.DispositivoCaracteristica;
import emp.rep.api.repository.DispositivoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DispositivoService {

    @Autowired
    private DispositivoRepository repositorio;

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

    public List<DispositivoDTO> obtenerTodo() {
        return repositorio.findAll().stream()
                .map(this::pasarDatos)
                .toList();
    }



    //Metodos de ayuda

    public DispositivoDTO pasarDatos(Dispositivo obj) {
        return new DispositivoDTO(
                obj.getSerial(),
                obj.getNombre(),
                obj.getTipo().getNombre(),
                obj.getFabricante().getNombre(),
                new ArrayList<>(obj.getCaracteristicas().stream()
                        .filter(DispositivoCaracteristica::isTieneCaracteristica)
                        .map(c -> c.getCaracteristica().getNombre())
                        .collect(Collectors.toList()))
        );
    }

}
