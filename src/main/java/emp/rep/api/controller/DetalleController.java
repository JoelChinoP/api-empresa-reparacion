package emp.rep.api.controller;

import emp.rep.api.dto.BasicoDTO;
import emp.rep.api.service.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleController {

    @Autowired
    private FabricanteService servicioFab;


    //@GetMapping("/fabricantes")
    //public ResponseEntity<List<BasicoDTO>>
}
