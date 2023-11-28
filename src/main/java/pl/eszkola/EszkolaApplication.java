package pl.eszkola;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import pl.eszkola.config.SecurityConfiguration;
@SpringBootApplication
@Import(SecurityConfiguration.class)
//@ComponentScan(basePackages = "pl.eszkola.controller")
@EntityScan(basePackages = "pl.eszkola.model")

public class EszkolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EszkolaApplication.class, args);
	}

}
