package emp.rep.api.controller;

import emp.rep.api.dto.BasicoDTO;
import emp.rep.api.dto.TipoDispositivoDTO;
import emp.rep.api.service.FabricanteService;
import emp.rep.api.service.CaracteristicaService;
import emp.rep.api.service.TipoDispositivoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleController {

    @Autowired
    private FabricanteService servicioFab;
    @Autowired
    private CaracteristicaService servicioCar;
    @Autowired
    private TipoDispositivoService servicioTip;


    //Tipos

    @GetMapping("/tiposDispositivos")
    public ResponseEntity<List<TipoDispositivoDTO>> obtenerTipos() {
        return ResponseEntity.ok(servicioTip.obtenerTodo());
    }

    @PostMapping("/tiposDispositivos")
    public ResponseEntity<BasicoDTO> agregarTipoDispositivo(@RequestBody @Validated BasicoDTO obj,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        BasicoDTO tipoNuevo = servicioTip.aniadir(obj);
        URI url = uriComponentsBuilder.path("/tiposDispositivos/{id}").buildAndExpand(tipoNuevo.id()).toUri();
        return ResponseEntity.created(url).body(tipoNuevo);
    }

    @GetMapping("/tiposDispositivos/{id}")
    public ResponseEntity<TipoDispositivoDTO> obtenerTipoDispositivoID(@PathVariable Integer id) {
        TipoDispositivoDTO tipoDispositivo = servicioTip.obtenerPorID(id).orElseThrow();
        return ResponseEntity.ok(tipoDispositivo);
    }

    @GetMapping("/tiposDispositivos/nombre/{nombre}")
    public ResponseEntity<TipoDispositivoDTO> obtenerTipoDispositivoNombre(@PathVariable String nombre) {
        TipoDispositivoDTO tipoDispositivo = servicioTip.obtenerPorNombre(nombre).orElseThrow();
        return ResponseEntity.ok(tipoDispositivo);
    }

    @GetMapping("/tiposDispositivos/{id}/caracteristicas")
    public ResponseEntity<List<String>> obtenerCaracteristicasTipoDispositivoID(@PathVariable Integer id) {
        List<String> caracteristicas = servicioTip.obtenerCaracteristicasPorID(id);
        return ResponseEntity.ok(caracteristicas);
    }

    @GetMapping("/tiposDispositivos/nombre/{nombre}/caracteristicas")
    public ResponseEntity<List<String>> obtenerCaracteristicasTipoDispositivoNombre(@PathVariable String nombre) {
        List<String> caracteristicas = servicioTip.obtenerCaracteristicasPorNombre(nombre);
        return ResponseEntity.ok(caracteristicas);
    }





    //Fabricantes

    @GetMapping("/fabricantes")
    public ResponseEntity<List<BasicoDTO>> obtenerFabricantes() {
        return ResponseEntity.ok(servicioFab.obtenerTodo());
    }

    @PostMapping("/fabricantes")
    public ResponseEntity<BasicoDTO> agregarFabricante(@RequestBody @Validated BasicoDTO obj,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        BasicoDTO fabricanteNuevo = servicioFab.aniadir(obj);
        URI url = uriComponentsBuilder.path("/fabricantes/{id}").buildAndExpand(fabricanteNuevo.id()).toUri();
        return ResponseEntity.created(url).body(fabricanteNuevo);
    }

    @GetMapping("/fabricantes/{id}")
    public ResponseEntity<BasicoDTO> obtenerFabricanteID(@PathVariable Integer id) {
        BasicoDTO fabricante = servicioFab.obtenerPorID(id).orElseThrow();
        return ResponseEntity.ok(fabricante);
    }

    @GetMapping("/fabricantes/nombre/{nombre}")
    public ResponseEntity<BasicoDTO> obtenerFabricanteNombre(@PathVariable String nombre) {
        BasicoDTO fabricante = servicioFab.obtenerPorNombre(nombre).orElseThrow();
        return ResponseEntity.ok(fabricante);
    }

    @PutMapping("/fabricantes")
    @Transactional
    public ResponseEntity<BasicoDTO> modificaFabricante(@RequestBody @Validated BasicoDTO obj,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        BasicoDTO fabricanteModificado = servicioFab.modificar(obj);
        URI url = uriComponentsBuilder.path("/fabricantes/{id}").buildAndExpand(fabricanteModificado.id()).toUri();
        return ResponseEntity.ok(fabricanteModificado);
    }

    @DeleteMapping("/fabricantes/{id}")
    public ResponseEntity<Void> eliminarFabricante(@PathVariable Integer id) {
        BasicoDTO fabricante = servicioFab.obtenerPorID(id).orElseThrow();
        servicioFab.eliminar(fabricante);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/fabricantes/nombre/{nombre}")
    public ResponseEntity<Void> eliminarFabricanteNombre(@PathVariable String nombre) {
        BasicoDTO fabricante = servicioFab.obtenerPorNombre(nombre).orElseThrow();
        servicioFab.eliminar(fabricante);
        return ResponseEntity.noContent().build();
    }


    //Caracteristicas

    @GetMapping("/caracteristicas")
    public ResponseEntity<List<BasicoDTO>> obtenerCaracteristicas() {
        return ResponseEntity.ok(servicioCar.obtenerTodo());
    }

    @PostMapping("/caracteristicas")
    public ResponseEntity<BasicoDTO> agregarCaracteristica(@RequestBody @Validated BasicoDTO obj,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        BasicoDTO caracteristicaNuevo = servicioCar.aniadir(obj);
        URI url = uriComponentsBuilder.path("/caracteristicas/{id}").buildAndExpand(caracteristicaNuevo.id()).toUri();
        return ResponseEntity.created(url).body(caracteristicaNuevo);
    }

    @GetMapping("/caracteristicas/{id}")
    public ResponseEntity<BasicoDTO> obtenerCaracteristicaID(@PathVariable Integer id) {
        BasicoDTO caracteristica = servicioCar.obtenerPorID(id).orElseThrow();
        return ResponseEntity.ok(caracteristica);
    }

    @GetMapping("/caracteristicas/nombre/{nombre}")
    public ResponseEntity<BasicoDTO> obtenerCaracteristicaNombre(@PathVariable String nombre) {
        BasicoDTO caracteristica = servicioCar.obtenerPorNombre(nombre).orElseThrow();
        return ResponseEntity.ok(caracteristica);
    }

    @PutMapping("/caracteristicas")
    @Transactional
    public ResponseEntity<BasicoDTO> modificaCaracteristica(@RequestBody @Validated BasicoDTO obj,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        BasicoDTO caracteristicaModificado = servicioCar.modificar(obj);
        URI url = uriComponentsBuilder.path("/caracteristicas/{id}").buildAndExpand(caracteristicaModificado.id()).toUri();
        return ResponseEntity.ok(caracteristicaModificado);
    }

    @DeleteMapping("/caracteristicas/{id}")
    public ResponseEntity<Void> eliminarCaracteristica(@PathVariable Integer id) {
        BasicoDTO caracteristicas = servicioCar.obtenerPorID(id).orElseThrow();
        servicioCar.eliminar(caracteristicas);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/caracteristicas/nombre/{nombre}")
    public ResponseEntity<Void> eliminarCaracteristicaNombre(@PathVariable String nombre) {
        BasicoDTO caracteristicas = servicioCar.obtenerPorNombre(nombre).orElseThrow();
        servicioCar.eliminar(caracteristicas);
        return ResponseEntity.noContent().build();
    }
}
