package be.hetnest.hetnestproject;

import be.hetnest.hetnestproject.dao.AanvragenRepository;
import be.hetnest.hetnestproject.dao.UserRepository;
import be.hetnest.hetnestproject.domain.Aanvraag;
import be.hetnest.hetnestproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class HetnestprojectApplicationInitDB  implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AanvragenRepository aanvragenRepository;

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

        System.out.println(" -- Database has been initialized --");
    }
}
