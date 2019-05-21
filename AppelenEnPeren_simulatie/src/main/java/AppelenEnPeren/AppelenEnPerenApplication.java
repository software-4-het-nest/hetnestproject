package AppelenEnPeren;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("be.odisee.appelenenperen")
@EntityScan("be.odisee.appelenenperen.domain")
public class AppelenEnPerenApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AppelenEnPerenApplication.class, args);
	}
	
	@Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }
	
}
