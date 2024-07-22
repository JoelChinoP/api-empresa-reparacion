package emp.rep.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenServicio ordenServicio;

    @Column(length = 40)
    private String serial;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private Dispositivo dispositivo;

    @Column(length = 200, nullable = false)
    private String descripcion;

    @Column(length = 200)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(length = 40, columnDefinition = "VARCHAR(40) DEFAULT 'RECIBIDO'")
    private EstadoDispositivo estado;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    public Servicio(OrdenServicio ordenServicio, String serial, Dispositivo dispositivo, String descripcion) {
        this.ordenServicio = ordenServicio;
        this.serial = serial;
        this.dispositivo = dispositivo;
        this.descripcion = descripcion;
    }
}
