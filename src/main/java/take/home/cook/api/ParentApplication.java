package take.home.cook.api;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import take.home.cook.api.model.user.domain.User;
import take.home.cook.api.model.user.domain.UserKey;
import take.home.cook.api.model.user.domain.UserType;
import take.home.cook.api.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ParentApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}

	@Override
	@Order(1)
	public void run(String... args) throws Exception {
		List<User> users = new ArrayList<>();
		userRepository.deleteAll();
		Stream.of(1,2,3,4,5,6,7,8,9).forEach(i -> {
			User user = new User(new UserKey(UUIDs.timeBased() , UserType.COOK.getType()) , String.format("User %s" , i ) , String.format("Publisher %s" , i ) , ""  , "");
			users.add(user);
		});

		userRepository.saveAll(users);
	}
}
