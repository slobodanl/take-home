package take.home.cook.api.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import take.home.cook.api.domain.Book;

import java.util.UUID;

@Repository
public interface BookRepository extends CassandraRepository<Book , UUID> {
    @Query("select * from book where title = ?0 and publisher=?1")
    Iterable<Book> findByTitleAndPublisher(String title, String publisher);
}