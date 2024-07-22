package emp.rep.api.dto;

import emp.rep.api.model.Caracteristica;
import emp.rep.api.model.Dispositivo;

import javax.sql.XAConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record DispositivoDTO (
        Long id,
        String nombre,
        String tipo,
        String fabricante,
        List<CaracteristicaDTO> caracteristicas
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DispositivoDTO that = (DispositivoDTO) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
