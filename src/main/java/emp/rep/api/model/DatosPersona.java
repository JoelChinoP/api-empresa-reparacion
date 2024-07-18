package emp.rep.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class DatosPersona {
    @Column(length = 40)
    private String nombre;

    @Column(length = 40)
    private String apellido;

    @Column(length = 15)
    private String telefono;

    @Column(length = 40)
    private String correo;
}
