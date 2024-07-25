package emp.rep.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Trabajador extends DatosPersona{
    @Id
    @Column(length = 8)
    private String id; //dni

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private Cargo cargo;

    @Column(columnDefinition = "boolean default true")
    private boolean activo;

    @OneToMany(mappedBy = "trabajador", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //nombre del objeto en la clase OrdenServicio
    private List<OrdenServicio> ordenServicios;

    @JsonIgnore
    @OneToOne(mappedBy = "trabajador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;
}
