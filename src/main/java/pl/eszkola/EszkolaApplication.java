package pl.eszkola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.eszkola.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class EszkolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EszkolaApplication.class, args);
	}

}
