package take.home.cook.api;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import take.home.cook.api.model.menu.MenuItem;
import take.home.cook.api.model.user.User;
import take.home.cook.api.model.user.UserKey;
import take.home.cook.api.model.user.UserType;
import take.home.cook.api.repository.menu.MenuItemByCategoryRepository;
import take.home.cook.api.repository.menu.MenuItemByUserRepository;
import take.home.cook.api.repository.menu.MenuItemRepository;
import take.home.cook.api.repository.user.UserByTypeRepository;
import take.home.cook.api.repository.user.UserRepository;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class ParentApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserByTypeRepository userByTypeRepository;

	@Autowired
	MenuItemRepository menuItemRepository;

	@Autowired
	MenuItemByUserRepository menuItemByUserRepository;

	@Autowired
	MenuItemByCategoryRepository menuItemByCategoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}

	@Override
	@Order(1)
	public void run(String... args) throws Exception {
		List<User> users = new ArrayList<>();

		Stream.of(1,2,3,4,5,6,7,8,9).forEach(i -> {
			User user = new User(new UserKey(UUIDs.timeBased() , UserType.COOK.getType()) , String.format("User %s" , i ) , String.format("Publisher %s" , i ) , ""  , "");
			users.add(user);
		});
		userRepository.deleteAll();
		userByTypeRepository.deleteAll();
		menuItemRepository.deleteAll();
		menuItemByCategoryRepository.deleteAll();
		userRepository.saveAll(users);
		System.out.println("~~~~Query by users~~~~");
		userRepository.findAll().forEach(System.out::println);
		System.out.println("~~~~Query by type~~~~");
		userByTypeRepository.findAllByUserByTypeKeyType(UserType.COOK.getType()).forEach(System.out::println);
		Set<String> cattegory = new HashSet<>();
		cattegory.add("Manja");
		cattegory.add("Hot");
		MenuItem menuItem = new MenuItem(new MenuItem.Key(null ,
				UUID.fromString("74535550-7f5b-11e8-a220-f7bdf40793cd") , new Date()) ,
				"http://item.123.jpg" ,
				"Test item" ,
				"Test desc" ,
				true , cattegory , Collections.emptySet() , Collections.emptySet());
		menuItemRepository.save(menuItem);
		System.out.println("~~~~Query by menu item id~~~~");
		menuItemRepository.findById(BasicMapId.id("id" , menuItem.getKey().getId())).ifPresent(System.out::println);
		System.out.println("~~~~Query by category~~~~");
		menuItemByCategoryRepository.findAll().forEach(System.out::println);
		// userByTypeRepository.findByUserKeyType(UserType.COOK.getType()).forEach(System.out::println);
		// userRepository.findByTitleAndPublisher("Test" , "test");
	}
}
