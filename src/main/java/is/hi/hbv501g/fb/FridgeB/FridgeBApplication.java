package is.hi.hbv501g.fb.FridgeB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FridgeBApplication {

	public static void main(String[] args) {
		SpringApplication.run(FridgeBApplication.class, args);
	}

}
