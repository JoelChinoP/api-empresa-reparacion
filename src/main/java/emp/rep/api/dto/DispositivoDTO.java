package emp.rep.api.dto;

import emp.rep.api.model.Dispositivo;

import java.util.ArrayList;

public record DispositivoDTO (
        String serial,
        String nombre,
        String tipo,
        String fabricante,
        ArrayList<String> caracteristicas
) {

}
