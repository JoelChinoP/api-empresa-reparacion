package emp.rep.api.dto;

import emp.rep.api.model.Dispositivo;
import emp.rep.api.model.EstadoDispositivo;

import java.math.BigDecimal;

public record ServicioDTO(
        String id,
        String serial,
        DispositivoDTO dispositivo,
        String descripcion,
        String observaciones,
        EstadoDispositivo estado,
        BigDecimal precio
) {
}
