package emp.rep.api.dto;

import emp.rep.api.model.Cliente;

public record ClienteDTO(
        String dni,
        String nombre,
        String telefono
) {

    public ClienteDTO(Cliente obj) {
        this(obj.getId(), obj.getNombre() + " " + obj.getApellido(), obj.getTelefono());
    }

}
