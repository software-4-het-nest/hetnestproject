package hetnestproject_consume;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
public class Hetnestproject_consumeApplication {

    public static void main(String[] args) {

        Aanvraag deAanvraag;
        Aanvraag[] aanvragen;

        RestTemplate rt = new RestTemplate();

        // REST GET vraagt een resource op (één item)

        deAanvraag = new Aanvraag("Appelsap", 12.5);


        // REST PUT past een resource aan

        deAanvraag.setVruchtensap("appelsap");

        rt.put("http://localhost:8080/aanvraag/{id}", deAanvraag, deAanvraag.getId());

        deAanvraag = rt.getForObject("http://localhost:8080/aanvrag/{id}", Aanvraag.class, 14);

        System.out.println("Via REST veranderden we deze aanvraag zijn vruchtensap naar: "
                + deAanvraag.getVruchtensap());

        // REST POST laat toe - naast vele andere dingen - om een resource aan te maken

        deAanvraag = new Aanvraag("perensap", 12.5);
        deAanvraag = rt.postForObject("http://localhost:8080/aanvraag", deAanvraag, Aanvraag.class);

        System.out.println("Nieuwe aanvraag toegevoegd: " + deAanvraag.getQuantity() + "L " + deAanvraag.getVruchtensap()+" "
                + " met als aanvraag id {"+deAanvraag.getId()+"}");
    }
}

