package emp.rep.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "dispositivo_caracteristica")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DispositivoCaracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private Dispositivo dispositivo;

    @ManyToOne
    @JoinColumn(name = "caracteristica_id", nullable = false)
    private Caracteristica caracteristica;

    @Column(length = 100)
    private String descripcion;

    @Column(name = "tiene_caracteristica")
    private boolean tieneCaracteristica; // Campo booleano para indicar si tiene la característica
}
