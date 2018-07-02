package take.home.cook.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import take.home.cook.api.repository.BookRepository;

@Component
@Order(2)
public class Listing implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        bookRepository.findAll().forEach(System.out::println);
        bookRepository.findByTitleAndPublisher("Test" , "test");
    }
}