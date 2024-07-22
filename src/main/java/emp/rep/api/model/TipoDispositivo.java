package emp.rep.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tipo_dispositivo")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TipoDispositivo {
    @Id
    @Column(columnDefinition = "MEDIUMINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, unique = true, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //nombre del objeto en la clase Dispositivo
    private List<Dispositivo> dispositivos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tipo_caracteristica",
            joinColumns = @JoinColumn(name = "tipo_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id")
    )
    private List<Caracteristica> caracteristicas = new ArrayList<>();

    public TipoDispositivo(String nombre) {
        this.nombre = nombre;
    }
}
