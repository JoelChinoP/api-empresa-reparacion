package emp.rep.api.dto;

import emp.rep.api.model.Cargo;
import emp.rep.api.model.Trabajador;

public record TrabajadorDTO(
        String dni,
        String nombre,
        String telefono,
        Cargo cargo
) {
    public TrabajadorDTO(Trabajador obj) {
        this(obj.getId(), obj.getNombre()+" "+obj.getApellido(), obj.getTelefono(), obj.getCargo());
    }
}
