package gr.hua.dit.ErasmusRequest;

import gr.hua.dit.ErasmusRequest.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ErasmusRequestApplication {

	public static void main(String[] args) {

		SpringApplication.run(ErasmusRequestApplication.class, args);
	}

}
