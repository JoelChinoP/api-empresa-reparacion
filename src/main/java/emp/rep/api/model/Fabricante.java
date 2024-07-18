package emp.rep.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Fabricante {
    @Id
    @Column(columnDefinition = "MEDIUMINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //nombre del objeto en la clase Dispositivo
    private List<Dispositivo> dispositivos = new ArrayList<>();
}
