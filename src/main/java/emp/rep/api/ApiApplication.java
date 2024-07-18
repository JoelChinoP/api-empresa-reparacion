package emp.rep.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import emp.rep.api.model.Cliente;

@SpringBootApplication
public class ApiApplication{

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
