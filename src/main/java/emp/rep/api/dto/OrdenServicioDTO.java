package emp.rep.api.dto;

import emp.rep.api.model.Empresa;

import java.time.LocalDate;
import java.util.List;


public record OrdenServicioDTO (
        String id,
        Empresa empresa,
        LocalDate fecha,
        ClienteDTO cliente,
        TrabajadorDTO trabajador,
        List<ServicioDTO> servicios
){



}
