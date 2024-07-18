package emp.rep.api.dto;

import emp.rep.api.model.Cliente;

public record ClienteDTO(
        String nombre,
        String telefono
) {

    public ClienteDTO(Cliente obj) {
        this(obj.getNombre() + " " + obj.getApellido(), obj.getTelefono());
    }

}
