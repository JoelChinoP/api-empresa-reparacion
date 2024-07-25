package emp.rep.api.controller;

import emp.rep.api.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping("/me")
    public ResponseEntity<?> obtenerUsuario(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (jwtTokenUtil.validateToken(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            return ResponseEntity.ok(Collections.singletonMap("user", username));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String zzz) {
        return ResponseEntity.ok("Logeado");
    }

}
