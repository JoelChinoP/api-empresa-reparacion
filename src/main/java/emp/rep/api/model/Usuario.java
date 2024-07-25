package emp.rep.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, unique = true)
    private String user;

    @Column(length = 200)
    private String password;

    @OneToOne
    @JoinColumn(name = "trabajador_id", nullable = false)
    private Trabajador trabajador;
}
