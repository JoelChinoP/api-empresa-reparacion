package emp.rep.api.service;

import emp.rep.api.dto.CaracteristicaDTO;
import emp.rep.api.model.Dispositivo;
import emp.rep.api.model.DispositivoCaracteristica;
import emp.rep.api.repository.CaracteristicaRepository;
import emp.rep.api.repository.DispositivoCaracteristicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DispositivoCaracteristicaService {
    @Autowired
    private DispositivoCaracteristicaRepository repositorio;
    @Autowired
    private CaracteristicaRepository repositorioCar;

    public DispositivoCaracteristica aniadir(DispositivoCaracteristica dispositivoCaracteristica) {
        return repositorio.save(dispositivoCaracteristica);
    }

    public void aniadirCaracteristicas(Dispositivo dispositivo, List<CaracteristicaDTO> caracteristicasDTO) {
        dispositivo.getTipo().getCaracteristicas().forEach(caracteristica -> {
            DispositivoCaracteristica dispositivoCaracteristica = new DispositivoCaracteristica();
            dispositivoCaracteristica.setDispositivo(dispositivo);
            dispositivoCaracteristica.setCaracteristica(caracteristica);

            caracteristicasDTO.stream()
                    .filter(c -> c.nombre().equals(caracteristica.getNombre()))
                    .findFirst()
                    .ifPresent(c -> {
                        dispositivoCaracteristica.setTieneCaracteristica(true);
                        dispositivoCaracteristica.setDescripcion(c.descripcion());
                    });

            aniadir(dispositivoCaracteristica);
        });
    }

}
