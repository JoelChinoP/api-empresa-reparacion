package emp.rep.api.controller;

import emp.rep.api.model.Empresa;
import emp.rep.api.service.EmpresaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService servicioEmp;

    @GetMapping
    public ResponseEntity<List<Empresa>> listadoEmpresas() {
        List<Empresa> lista = servicioEmp.obtenerTodo();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorID(@PathVariable String id) {
        Optional<Empresa> empresa = servicioEmp.obtenerPorID(id);
        return empresa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> agregarEmpresa(@RequestBody Empresa empresa) {
        Empresa nuevaEmpresa = servicioEmp.aniadir(empresa);
        return ResponseEntity.ok(nuevaEmpresa);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Empresa> modificarEmpresa(@RequestBody Empresa empresa) {
        Empresa empresaModificada = servicioEmp.modificar(empresa);
        return ResponseEntity.ok(empresaModificada);
    }
}
