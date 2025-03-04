package emp.rep.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrdenServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, updatable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "trabajador_id", nullable = false)
    private Trabajador trabajador;

    @OneToMany(mappedBy = "ordenServicio", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //nombre del objeto en la clase Servicio
    private List<Servicio> servicios;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDate.now();
    }

    public OrdenServicio(Empresa empresa, Cliente cliente, Trabajador trabajador) {
        this.empresa = empresa;
        this.cliente = cliente;
        this.trabajador = trabajador;
    }
}

