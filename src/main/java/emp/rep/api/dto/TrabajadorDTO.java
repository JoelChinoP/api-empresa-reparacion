package emp.rep.api.dto;

import emp.rep.api.model.Cargo;
import emp.rep.api.model.Trabajador;

public record TrabajadorDTO(
        String nombre,
        String telefono,
        Cargo cargo
) {
    public TrabajadorDTO(Trabajador obj) {
        this(obj.getNombre()+" "+obj.getApellido(), obj.getTelefono(), obj.getCargo());
    }
}
