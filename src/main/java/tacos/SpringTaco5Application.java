package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"web","tacos"})
public class SpringTaco5Application {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringTaco5Application.class, args);
		
		String encode= new BCryptPasswordEncoder().encode("coco");
		
		System.out.println(encode);
	}

}
