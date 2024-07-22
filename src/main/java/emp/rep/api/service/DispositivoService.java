package emp.rep.api.service;

import emp.rep.api.dto.CaracteristicaDTO;
import emp.rep.api.dto.DispositivoDTO;
import emp.rep.api.model.Dispositivo;
import emp.rep.api.model.DispositivoCaracteristica;
import emp.rep.api.model.Fabricante;
import emp.rep.api.model.TipoDispositivo;
import emp.rep.api.repository.CaracteristicaRepository;
import emp.rep.api.repository.DispositivoRepository;
import emp.rep.api.repository.FabricanteRepository;
import emp.rep.api.repository.TipoDispositivoRepository;
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
    @Autowired
    private CaracteristicaRepository repositorioCar;
    @Autowired
    private FabricanteRepository repositorioFab;
    @Autowired
    private TipoDispositivoRepository repositorioTipo;
    @Autowired
    private DispositivoCaracteristicaService servicioDisCar;

    public Dispositivo aniadir(DispositivoDTO obj) {
        Dispositivo nuevo = repositorio.save(pasarDatosDTO(obj));
        servicioDisCar.aniadirCaracteristicas(nuevo, obj.caracteristicas());
        return nuevo;
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
                .map(this::pasarDatosModel)
                .toList();
    }



    //Metodos de ayuda

    public DispositivoDTO pasarDatosModel(Dispositivo obj) {
        return new DispositivoDTO(
                obj.getId(),
                obj.getNombre(),
                obj.getTipo().getNombre(),
                obj.getFabricante().getNombre(),
                new ArrayList<>(obj.getCaracteristicas().stream()
                        .filter(DispositivoCaracteristica::isTieneCaracteristica)
                        .map(c -> new CaracteristicaDTO(c.getCaracteristica().getNombre(), c.getDescripcion()))
                        .collect(Collectors.toList()))
        );
    }

    public Dispositivo pasarDatosDTO(DispositivoDTO obj) {
        Fabricante fabricante = repositorioFab.findByNombreIgnoreCase(obj.fabricante())
                .orElseThrow(() -> new RuntimeException("Fabricante no encontrado"));

        TipoDispositivo tipo = repositorioTipo.findByNombreIgnoreCase(obj.tipo())
                .orElseThrow(() -> new RuntimeException("Tipo de dispositivo no encontrado"));

        return new Dispositivo(obj.nombre(), fabricante, tipo);
    }

}
