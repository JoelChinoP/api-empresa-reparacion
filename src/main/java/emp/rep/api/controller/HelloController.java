package emp.rep.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {
    @GetMapping
    public String helloWorld() {
        return "<h1>Hola Mundo!!!</h1>";
    }
}
