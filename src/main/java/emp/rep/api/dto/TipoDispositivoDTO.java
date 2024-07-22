package emp.rep.api.dto;

import java.util.List;

public record TipoDispositivoDTO(
        Integer id,
        String nombre,
        List<String> caracteristicas
) {
    public TipoDispositivoDTO(Integer id, String nombre) {
        this(id, nombre, List.of());
    }
}
