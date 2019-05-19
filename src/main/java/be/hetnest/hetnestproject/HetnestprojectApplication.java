package be.hetnest.hetnestproject;

import be.hetnest.hetnestproject.domain.Aanbieding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages= {"be.hetnest.hetnestproject.controller", "be.hetnest.hetnestproject.service","be.hetnest.hetnestproject"})
public class HetnestprojectApplication {

	public static void main(String[] args) {

		Aanbieding[] Aanbiedingen;

		RestTemplate rt = new RestTemplate();

		// REST GET vraagt een resource op (één item)

		Aanbiedingen = rt.getForObject("http://localhost/test.json", Aanbieding[].class);
		System.out.println("Via REST haalden we volgende Aanbieding op: "+ Aanbiedingen[0]);

		//halen de lijst op
		Aanbiedingen = rt.getForObject("http://localhost/test.json", Aanbieding[].class);
		for (Aanbieding p : Aanbiedingen) {
			System.out.println(p);
		}

		//rest put
		Aanbieding deAanbieding = new Aanbieding(Aanbiedingen[0].getHoeveelheid(),Aanbiedingen[0].getStatus(), Aanbiedingen[0].getPrijs(), Aanbiedingen[0].getType(), Aanbiedingen[0].getNaam());
		SpringApplication.run(HetnestprojectApplication.class, args);
	}

}
