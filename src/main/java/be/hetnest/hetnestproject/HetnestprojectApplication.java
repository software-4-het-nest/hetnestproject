package be.hetnest.hetnestproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"be.hetnest.hetnestproject.controller", "be.hetnest.hetnestproject.service"}) 
public class HetnestprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HetnestprojectApplication.class, args);
	}

}
