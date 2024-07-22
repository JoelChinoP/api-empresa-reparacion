package emp.rep.api.controller;

import emp.rep.api.dto.DispositivoDTO;
import emp.rep.api.model.Dispositivo;
import emp.rep.api.model.DispositivoCaracteristica;
import emp.rep.api.service.DispositivoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService servicioDis;

    @GetMapping
    public ResponseEntity<List<DispositivoDTO>> listadoClientes() {
        return ResponseEntity.ok(servicioDis.obtenerTodo());
    }


    @PostMapping
    public ResponseEntity<DispositivoDTO> agregarDispositivo(@RequestBody @Validated DispositivoDTO obj,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        Dispositivo nuevoDispositivo = servicioDis.aniadir(obj);
        URI url = uriComponentsBuilder.path("/dispositivos/{id}")
                .buildAndExpand(nuevoDispositivo.getId()).toUri();
        return ResponseEntity.created(url).body(servicioDis.pasarDatosModel(nuevoDispositivo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoDTO> obtenerDispositivo(@PathVariable Long id) {
        Dispositivo dispositivo = servicioDis.obtenerPorID(id).orElseThrow();
        return ResponseEntity.ok(servicioDis.pasarDatosModel(dispositivo));
    }


    /*
    @PostMapping
    public ResponseEntity<Dispositivo> agregarDispositivo(@RequestBody @Validated Dispositivo obj,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        Cliente nuevoCliente = servicioCli.aniadir(obj);
        URI url = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(nuevoCliente.getId()).toUri();
        return ResponseEntity.created(url).body(nuevoCliente);
    }

    @GetMapping("/clientes/{dni}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String dni) {
        Cliente cliente = servicioCli.obtenerPorDNI(dni).orElseThrow();
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/clientes")
    @Transactional
    public ResponseEntity<Cliente> modificarCliente(@RequestBody @Validated Cliente obj,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        Cliente clienteModificado = servicioCli.modificar(obj);
        URI url = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(clienteModificado.getId()).toUri();
        return ResponseEntity.ok(clienteModificado);
    }

    @DeleteMapping("/clientes/{dni}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String dni) {
        Cliente cliente = servicioCli.obtenerPorDNI(dni).orElseThrow();
        servicioCli.eliminar(cliente);
        return ResponseEntity.noContent().build();
    }*/

}
