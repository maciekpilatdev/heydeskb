package pl.org.conceptweb.heydeskb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HeydeskbApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(HeydeskbApplication.class, args);
	}

}
