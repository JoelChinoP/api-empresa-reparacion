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
public class Caracteristica {
    @Id
    @Column(columnDefinition = "MEDIUMINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, unique = true, nullable = false)
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tipo_caracteristica",
            joinColumns = @JoinColumn(name = "caracteristica_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private List<TipoDispositivo> tipos = new ArrayList<>();


    @OneToMany(mappedBy = "caracteristica", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //nombre del objeto en la clase OrdenServicio
    private List<DispositivoCaracteristica> dispositivos;

    public Caracteristica(String nombre) {
        this.nombre = nombre;
    }

    public Caracteristica(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
