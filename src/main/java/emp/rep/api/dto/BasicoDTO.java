package emp.rep.api.dto;

import emp.rep.api.model.Caracteristica;
import emp.rep.api.model.Fabricante;
import emp.rep.api.model.TipoDispositivo;

public record BasicoDTO(
        Integer id,
        String nombre
) {
    public BasicoDTO (Caracteristica objeto) {
        this(objeto.getId(), objeto.getNombre());
    }

    public BasicoDTO (Fabricante objeto) {
        this(objeto.getId(), objeto.getNombre());
    }

    public BasicoDTO (TipoDispositivo objeto) {
        this(objeto.getId(), objeto.getNombre());
    }


}
