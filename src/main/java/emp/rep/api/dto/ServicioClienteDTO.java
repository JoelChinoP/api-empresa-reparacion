package emp.rep.api.dto;

import java.time.LocalDate;
import java.util.Date;

public record ServicioClienteDTO(
        String serial,
        String cliente,
        String trabajador,
        LocalDate fecha,
        String nombreDispositivo,
        String fabricante,
        String tipo,
        String descripcion,
        String estado
) {
}
