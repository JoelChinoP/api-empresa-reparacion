package emp.rep.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cliente extends DatosPersona{
    @Id
    @Column(length = 8)
    private String id; //dni

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //nombre del objeto en la clase OrdenServicio
    private List<OrdenServicio> ordenServicios;
}
