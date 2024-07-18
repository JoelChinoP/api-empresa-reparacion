package emp.rep.api.controller;

import emp.rep.api.dto.ClienteDTO;
import emp.rep.api.dto.TrabajadorDTO;
import emp.rep.api.model.Cliente;
import emp.rep.api.model.Trabajador;
import emp.rep.api.service.ClienteService;
import emp.rep.api.service.TrabajadorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private TrabajadorService servicioTra;
    @Autowired
    private ClienteService servicioCli;


    //Endpoints para clientes

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody @Validated Cliente obj,
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
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> listadoClientes() {
        List<ClienteDTO> lista = servicioCli.obtenerTodo().stream()
                .map(ClienteDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }


    // Endpoints para trabajadores

    @PostMapping("/trabajadores")
    public ResponseEntity<Trabajador> agregarTrabajador(@RequestBody @Validated Trabajador obj,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        Trabajador nuevoTrabajador = servicioTra.aniadir(obj);
        URI url = uriComponentsBuilder.path("/trabajadores/{id}").buildAndExpand(nuevoTrabajador.getId()).toUri();
        return ResponseEntity.created(url).body(nuevoTrabajador);
    }

    @GetMapping("/trabajadores/{dni}")
    public ResponseEntity<Trabajador> obtenerTrabajador(@PathVariable String dni) {
        Trabajador trabajador = servicioTra.obtenerPorDNI(dni).orElseThrow();
        return ResponseEntity.ok(trabajador);
    }

    @PutMapping("/trabajadores")
    @Transactional
    public ResponseEntity<Trabajador> modificarTrabajador(@RequestBody @Validated Trabajador obj,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        Trabajador trabajadorModificado = servicioTra.modificar(obj);
        URI url = uriComponentsBuilder.path("/trabajadores/{id}").buildAndExpand(trabajadorModificado.getId()).toUri();
        return ResponseEntity.ok(trabajadorModificado);
    }

    @DeleteMapping("/trabajadores/{dni}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable String dni) {
        Trabajador trabajador = servicioTra.obtenerPorDNI(dni).orElseThrow();
        servicioTra.eliminar(trabajador);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/trabajadores")
    public ResponseEntity<List<TrabajadorDTO>> listadoTrabajadores() {
        List<TrabajadorDTO> lista = servicioTra.obtenerTodo().stream()
                .map(TrabajadorDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }








}
