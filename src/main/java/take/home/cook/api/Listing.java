package take.home.cook.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import take.home.cook.api.model.user.domain.UserType;
import take.home.cook.api.repository.UserRepository;

@Component
@Order(2)
public class Listing implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findAll().forEach(System.out::println);
        userRepository.findByUserKeyType(UserType.COOK.getType()).forEach(System.out::println);
       // userRepository.findByTitleAndPublisher("Test" , "test");
    }
}