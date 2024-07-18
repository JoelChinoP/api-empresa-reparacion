package emp.rep.api.repository;

import emp.rep.api.model.Caracteristica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CaracteristicaRepositoryTest {

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Test
    void testAddTipoDispositivo() {
        String nombre = "Pantalla";

        // Crear una nueva Caracteristica
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setNombre(nombre);

        // Guardar la Caracteristica
        var savedCaracteristica = caracteristicaRepository.save(caracteristica);

        Assertions.assertNotNull(savedCaracteristica);
        Assertions.assertEquals(nombre, savedCaracteristica.getNombre());
    }
}
