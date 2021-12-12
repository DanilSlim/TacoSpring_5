package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"web","tacos"})
public class SpringTaco5Application {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringTaco5Application.class, args);
	}

}
