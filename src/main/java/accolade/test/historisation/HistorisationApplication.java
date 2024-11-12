package accolade.test.historisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"accolade.test.repository", "accolade.test.historisation.Repository"})
@EntityScan(basePackages = {"accolade.test.entity", "accolade.test.historisation.Entity"})
@ComponentScan(basePackages = {"accolade.test", "accolade.test.historisation"})
public class HistorisationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorisationApplication.class, args);
	}
}
