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
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String serial;

    @Column(length = 40)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "fabricante_id", nullable = false)
    Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    TipoDispositivo tipo;

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private EstadoDispositivo estado;

    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //nombre del objeto en la clase OrdenServicio
    private List<DispositivoCaracteristica> caracteristicas;

    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //nombre del objeto en la clase Servicio
    private List<Servicio> servicios;
}