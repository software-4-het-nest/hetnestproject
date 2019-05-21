package hetnestproject_consume;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
public class Hetnestproject_consumeApplication {

    public static void main(String[] args) {

        Aanvraag deAanvraag;
        RestTemplate rt = new RestTemplate();


        // REST POST laat toe - naast vele andere dingen - om een resource aan te maken

        deAanvraag = new Aanvraag(12,10,"dringend","perensap");
        deAanvraag = rt.postForObject("http://localhost:8081/aanvraag", deAanvraag, Aanvraag.class);

        System.out.println("Nieuwe aanvraag toegevoegd: " + deAanvraag.getHoeveelheid() + "L " + deAanvraag.getNaam());
    }
}

