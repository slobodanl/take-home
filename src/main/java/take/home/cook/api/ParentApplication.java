package take.home.cook.api;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import take.home.cook.api.domain.Book;
import take.home.cook.api.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ParentApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}

	@Override
	@Order(1)
	public void run(String... args) throws Exception {
		List<Book> books = new ArrayList<>();
		bookRepository.deleteAll();
		Stream.of(1,2,3,4,5,6,7,8,9).forEach(i -> {
			Book book = new Book(UUIDs.timeBased() , String.format("Book %s" , i ) , String.format("Publisher %s" , i ) , Collections.emptySet());
			books.add(book);
		});

		bookRepository.saveAll(books);
	}
}
