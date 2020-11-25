package pl.com.conceptweb.uniappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.com.conceptweb.uniappbackend.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class UniappbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniappbackendApplication.class, args);
	}

}
