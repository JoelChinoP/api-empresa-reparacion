package emp.rep.api.service;

import emp.rep.api.dto.*;
import emp.rep.api.model.OrdenServicio;
import emp.rep.api.model.Servicio;
import emp.rep.api.repository.OrdenServicioRepository;
import emp.rep.api.repository.ServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdenServicioService {
    @Autowired
    private OrdenServicioRepository repositorioOrd;
    @Autowired
    private ServicioRepository repositorioSer;
    @Autowired
    private EmpresaService servicioEmp;
    @Autowired
    private ClienteService servicioCli;
    @Autowired
    private TrabajadorService servicioTra;
    @Autowired
    private DispositivoService servicioDis;

    public ServicioClienteDTO busquedaServicioPorSerial(String serial) {
        Servicio servicio = repositorioSer.findBySerial(serial).orElseThrow();
        return pasarDatosBusquedaCliente(servicio);
    }

    public OrdenServicioDTO crearOrden(OrdenServicioDTO orden) {
        var ordenCab = pasarDatosDTO(orden);
        var servicios = orden.servicios();
        List<Servicio> nuevosServicios = new ArrayList<>();

        if (!servicios.isEmpty()) {
            var nuevaOrden = repositorioOrd.save(ordenCab);

            servicios.forEach(servicio -> {
                var nuevoDispositivo = servicioDis.aniadir(servicio.dispositivo());
                var serial = generarSerialDispositivo(orden.cliente().dni(), nuevoDispositivo.getFabricante().getNombre());
                var nuevoServicio = new Servicio(
                        nuevaOrden,
                        serial,
                        nuevoDispositivo,
                        servicio.descripcion()
                );
                nuevosServicios.add(repositorioSer.save(nuevoServicio));
            });
        }

        return orden;
    }

    // Metodos adicionales
    public ServicioClienteDTO pasarDatosBusquedaCliente(Servicio servicio) {
        return new ServicioClienteDTO(
                servicio.getSerial(),
                servicio.getOrdenServicio().getCliente().getNombre(),
                servicio.getOrdenServicio().getTrabajador().getNombre(),
                servicio.getOrdenServicio().getFecha(),
                servicio.getDispositivo().getNombre(),
                servicio.getDispositivo().getFabricante().getNombre(),
                servicio.getDispositivo().getTipo().getNombre(),
                servicio.getDescripcion(),
                servicio.getEstado().toString()
        );
    }

    public OrdenServicio pasarDatosDTO(OrdenServicioDTO orden) {
        var empresa = servicioEmp.obtenerPorID(orden.empresa().getId()).orElseThrow();
        var cliente = servicioCli.obtenerPorDNI(orden.cliente().dni()).orElseThrow();
        var trabajador = servicioTra.obtenerPorDNI(orden.trabajador().dni()).orElseThrow();

        return new OrdenServicio(
                empresa,
                cliente,
                trabajador
        );
    }

    public OrdenServicioDTO pasarDatosModel(OrdenServicio orden, List<Servicio> servicios) {
        ClienteDTO clienteDTO = new ClienteDTO(orden.getCliente()); // Convert Cliente to ClienteDTO
        TrabajadorDTO trabajadorDTO = new TrabajadorDTO(orden.getTrabajador()); // Convert Trabajador to TrabajadorDTO

        return new OrdenServicioDTO(
                orden.getId().toString(),
                orden.getEmpresa(),
                orden.getFecha(),
                clienteDTO,
                trabajadorDTO,
                servicios.stream().map(this::pasarDatosServicio).collect(Collectors.toList())
        );
    }

    public ServicioDTO pasarDatosServicio(Servicio servicio) {
        DispositivoDTO dispositivoDTO = servicioDis.pasarDatosModel(servicio.getDispositivo()); // Convert Dispositivo to DispositivoDTO

        return new ServicioDTO(
                servicio.getId().toString(),
                servicio.getSerial(),
                dispositivoDTO,
                servicio.getDescripcion(),
                servicio.getObservaciones(),
                servicio.getEstado(),
                servicio.getPrecio()
        );
    }


    private String generarSerialDispositivo(String dniCliente, String fabricante) {
        String tipoFormateado = fabricante.trim().replace(" ", "0");
        if (tipoFormateado.length() > 4) {
            tipoFormateado = tipoFormateado.substring(0, 4);
        }

        dniCliente = dniCliente.substring(0,2) + dniCliente.substring(5,8);

        // Generamos un UUID y tomamos los primeros 8 caracteres
        String uuidParte = generarUUIDCorto(8);

        // Concatenamos las partes
        return tipoFormateado + "-" + dniCliente + "-" + uuidParte;
    }

    private String generarUUIDCorto(int longitud) {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        return uuidStr.substring(0, Math.min(longitud, uuidStr.length()));
    }
}
