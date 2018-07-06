package take.home.cook.api;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import take.home.cook.api.model.menu.MenuItem;
import take.home.cook.api.model.order.Order;
import take.home.cook.api.model.order.OrderLine;
import take.home.cook.api.model.user.User;
import take.home.cook.api.model.user.UserType;
import take.home.cook.api.repository.menu.MenuItemByCategoryRepository;
import take.home.cook.api.repository.menu.MenuItemByUserRepository;
import take.home.cook.api.repository.menu.MenuItemRepository;
import take.home.cook.api.repository.order.OrderByCookRepository;
import take.home.cook.api.repository.order.OrderByItemRepository;
import take.home.cook.api.repository.order.OrderByUserRepository;
import take.home.cook.api.repository.order.OrderRepository;
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

	@Autowired
    OrderRepository orderRepository;

	@Autowired
    OrderByCookRepository orderByCookRepository;

	@Autowired
    OrderByItemRepository orderByItemRepository;

	@Autowired
    OrderByUserRepository orderByUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}

	@Override

	public void run(String... args) throws Exception {
		List<User> users = new ArrayList<>();

		Stream.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15).forEach(i -> {
			User user = new User(new User.Key(UUIDs.timeBased() , UserType.values()[(new Random()).nextInt(3)].getType()) , String.format("User %s" , i ) , String.format("Publisher %s" , i ) , ""  , "");
			users.add(user);
		});

		userRepository.deleteAll();
		userByTypeRepository.deleteAll();
		menuItemRepository.deleteAll();
		menuItemByCategoryRepository.deleteAll();
		userRepository.saveAll(users);
		System.out.println("~~~~Query by users~~~~");
		List<User> userList = userRepository.findAll();
		userList.forEach(System.out::println);
		System.out.println("~~~~Query by type~~~~");
		userByTypeRepository.findAllByUserByTypeKeyType(UserType.COOK.getType()).forEach(System.out::println);
		Set<String> cattegory = new HashSet<>();
		cattegory.add("Manja");
		cattegory.add("Hot");
		MenuItem menuItem = new MenuItem(new MenuItem.Key(null ,
				userList.get(1).getId().getId() , new Date()) ,
				"http://item.123.jpg" ,
				"Test item" ,
				"Test desc" ,
				true , cattegory , Collections.emptySet() , Collections.emptySet());
		menuItemRepository.save(menuItem);
		System.out.println("~~~~Query by menu item id~~~~");
		menuItemRepository.findById(BasicMapId.id("id" , menuItem.getKey().getId())).ifPresent(System.out::println);
		System.out.println("~~~~Query by category~~~~");
		menuItemByCategoryRepository.findAll().forEach(System.out::println);
        System.out.println("~~~~ Insert by Order~~~~");
        Order order = new Order();
        order.setUserId(userList.get(0).getId().getId());
        order.setDeliveryAddress("Video 213-213");
        order.setDeliveryDate(new Date());
        order.setId(new Order.Key(UUIDs.timeBased() , new Date()));
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(new OrderLine(menuItem.getKey().getId() , userList.get(1).getId().getId(), 3L));
        order.setOrderLines(orderLines);
        orderRepository.save(order);
        System.out.println("~~~~Query by OrderByCook~~~~");
        orderByCookRepository.findAll().forEach(System.out::println);
        System.out.println("~~~~Query by OrderByItem~~~~");
        orderByItemRepository.findAll().forEach(System.out::println);
        System.out.println("~~~~Query by OrderByUser~~~~");
        orderByUserRepository.findAll().forEach(System.out::println);
	}
}
