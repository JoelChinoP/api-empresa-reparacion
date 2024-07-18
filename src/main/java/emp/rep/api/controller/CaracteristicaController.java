package emp.rep.api.controller;

import emp.rep.api.dto.CaracteristicaDTO;
import emp.rep.api.model.Caracteristica;
import emp.rep.api.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService servicioCaract;

    @GetMapping
    public ResponseEntity<List<CaracteristicaDTO>> listadoCaracteristicas() {
        List<CaracteristicaDTO> lista = servicioCaract.obtenerTodo().stream()
                .map(CaracteristicaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<CaracteristicaDTO> agregarCaracteristica(@RequestBody @Validated CaracteristicaDTO caracteristicaDTO,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        Caracteristica nuevaCaracteristica = servicioCaract.aniadir(new Caracteristica(caracteristicaDTO.nombre()));
        CaracteristicaDTO nuevaCaracteristicaDTO = new CaracteristicaDTO(nuevaCaracteristica.getId(), nuevaCaracteristica.getNombre());

        URI url = uriComponentsBuilder.path("/caracteristicas/{id}").buildAndExpand(nuevaCaracteristica.getId()).toUri();
        return ResponseEntity.created(url).body(nuevaCaracteristicaDTO);
    }
}
