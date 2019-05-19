package be.hetnest.hetnestproject;

import be.hetnest.hetnestproject.dao.*;
import be.hetnest.hetnestproject.domain.*;
import be.hetnest.hetnestproject.service.HetNestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class HetnestprojectApplicationInitDB  implements CommandLineRunner {

    @Autowired
    private HetNestService hetNestService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AanvragenRepository aanvragenRepository;

    @Autowired
    AanbiedingRepository aanbiedingRepository;

    @Autowired
    BrouwselRepository brouwselRepository;

    @Autowired
    IngredientenRepository ingredientRepository;

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User(1, "someone","{bcrypt}" + hash("pw"), "PRODUCTIELID");
        User user2  = new User(2,"someone2", "{bcrypt}" +  hash("pw2"),"MAGAZIJNIER");
        userRepository.save(user);
        userRepository.save(user2);

        Aanvraag aanvraag = new Aanvraag(10, 20.5, "TestType", "TestIngredient");
        Aanvraag aanvraag2 = new Aanvraag(10, 20.5, "TestType2", "TestIngredient2");
        aanvragenRepository.save(aanvraag);
        aanvragenRepository.save(aanvraag2);

        Aanbieding aanbieding = new Aanbieding(10, "nieuw", 5.2, "Externe Brouwer", "Hop IBU 10");
        Aanbieding aanbieding2 = new Aanbieding(20, "nieuw", 10, "Externe Brouwer", "Hop IBU 20");
        Aanbieding aanbieding3 = new Aanbieding(30, "nieuw", 22, "Externe Brouwer", "Hop IBU 22");
        Aanbieding aanbieding4 = new Aanbieding(40, "nieuw", 8, "Externe Brouwer", "Hop IBU 60");
        Aanbieding aanbieding5 = new Aanbieding(20, "nieuw", 5, "Externe Brouwer", "Hop IBU 80");
        Aanbieding aanbieding6 = new Aanbieding(30, "goedgekeurd", 0.5, "Leverancier", "Appelen");
        Aanbieding aanbieding7 = new Aanbieding(10, "goedgekeurd", 1.2, "Leverancier", "Peren");
        Aanbieding aanbieding8 = new Aanbieding(60, "goedgekeurd", 9.99, "Leverancier", "Kersensap");
        Aanbieding aanbieding9 = new Aanbieding(80, "dringend", 5, "Leverancier", "Gist");
        Aanbieding aanbieding10 = new Aanbieding(100, "dringend", 6, "Leverancier", "Suiker");
        aanbiedingRepository.save(aanbieding);
        aanbiedingRepository.save(aanbieding2);
        aanbiedingRepository.save(aanbieding3);
        aanbiedingRepository.save(aanbieding4);
        aanbiedingRepository.save(aanbieding5);
        aanbiedingRepository.save(aanbieding6);
        aanbiedingRepository.save(aanbieding7);
        aanbiedingRepository.save(aanbieding8);
        aanbiedingRepository.save(aanbieding9);
        aanbiedingRepository.save(aanbieding10);

        Ingredient ingredient = new Ingredient("graan", 0);
        Ingredient ingredient1 = new Ingredient("hop", 0);
        Ingredient ingredient2 = new Ingredient("gist", 0);
        Ingredient ingredient3 = new Ingredient("Appelen", aanbieding6.getHoeveelheid());
        Ingredient ingredient4 = new Ingredient("Peren", aanbieding7.getHoeveelheid());
        Ingredient ingredient5 = new Ingredient("Kersensap", aanbieding8.getHoeveelheid());
        Ingredient ingredient6 = new Ingredient("Suiker", 0);


        ingredientRepository.save(ingredient);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
        ingredientRepository.save(ingredient4);
        ingredientRepository.save(ingredient5);
        ingredientRepository.save(ingredient6);

        Brouwsel brouwsel1 = new Brouwsel("Hamad", "Kurbanov", null);
        Brouwsel brouwsel2 = new Brouwsel("Hamad", "Kurbanov", null);
        Brouwsel brouwsel3 = new Brouwsel("Hamad", "Kurbanov", null);

        brouwselRepository.save(brouwsel1);
        brouwselRepository.save(brouwsel2);
        brouwselRepository.save(brouwsel3);

        System.out.println(" -- Database has been initialized --");
    }
}
