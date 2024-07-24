package emp.rep.api.controller;

import emp.rep.api.dto.OrdenServicioDTO;
import emp.rep.api.dto.ServicioClienteDTO;
import emp.rep.api.service.OrdenServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private OrdenServicioService servicioOrd;

    @GetMapping("/busqueda/{serial}")
    public ResponseEntity<ServicioClienteDTO> busquedaOrdenPorSerial(@PathVariable String serial) {
        ServicioClienteDTO busquedaCliente = servicioOrd.busquedaServicioPorSerial(serial);
        return ResponseEntity.ok(busquedaCliente);
    }

    @PostMapping("/crear")
    public ResponseEntity<OrdenServicioDTO> crearOrden(@RequestBody OrdenServicioDTO orden,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        OrdenServicioDTO nuevaOrden = servicioOrd.crearOrden(orden);
        URI url = uriComponentsBuilder.path("/dispositivos/{id}")
                .buildAndExpand(nuevaOrden.id()).toUri();
        return ResponseEntity.created(url).body(nuevaOrden);
    }







}
