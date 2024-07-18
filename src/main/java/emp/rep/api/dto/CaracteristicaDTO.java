package emp.rep.api.dto;

import emp.rep.api.model.Caracteristica;

public record CaracteristicaDTO(
        Integer id,
        String nombre
) {
    public CaracteristicaDTO (Caracteristica objeto) {
        this(objeto.getId(), objeto.getNombre());
    }
}
