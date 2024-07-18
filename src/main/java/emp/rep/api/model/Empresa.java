package emp.rep.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Empresa {
    @Id
    @Column(length = 15)
    private String id; //ruc

    @Column(length = 40)
    private String nombre;

    @Column(length = 50)
    private String propietario;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String direccion;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //nombre del objeto en la clase OrdenServicio
    @JsonIgnore
    private List<OrdenServicio> ordenServicios;
}
