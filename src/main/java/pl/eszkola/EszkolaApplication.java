package pl.eszkola;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.eszkola.controller" , "pl.eszkola.service", "pl.eszkola.config", "pl.eszkola.templates", "pl.eszkola.model" })
public class EszkolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EszkolaApplication.class, args);
	}

}
