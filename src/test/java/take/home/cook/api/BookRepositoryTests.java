package take.home.cook.api;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.google.common.collect.ImmutableSet;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.cql.CqlIdentifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import take.home.cook.api.domain.Book;
import take.home.cook.api.repository.BookRepository;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CassandraConfig.class)
@TestExecutionListeners(
		listeners = OrderedCassandraTestExecutionListener.class,
		mergeMode = MERGE_WITH_DEFAULTS
)
@EmbeddedCassandra
public class BookRepositoryTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryTests.class);
	public static final String KEYSPACE = "thcKeyspaceTest";

	public static final String KEYSPACE_CREATION_QUERY = String.format("CREATE KEYSPACE IF NOT EXISTS %s WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '3' };" , KEYSPACE);

	public static final String KEYSPACE_ACTIVATE_QUERY = String.format("USE %s;" , KEYSPACE);

	public static final String DATA_TABLE_NAME = "book";

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CassandraAdminOperations adminTemplate;

	//

	@BeforeClass
	public static void startCassandraEmbedded() throws InterruptedException, TTransportException, ConfigurationException, IOException {
		EmbeddedCassandraServerHelper.startEmbeddedCassandra();
		final Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9142).build();
		LOGGER.info("Server Started at 127.0.0.1:9142... ");
		final Session session = cluster.connect();
		session.execute(KEYSPACE_CREATION_QUERY);
		session.execute(KEYSPACE_ACTIVATE_QUERY);
		LOGGER.info("KeySpace created and activated.");
		Thread.sleep(5000);
	}

	@Before
	public void createTable() throws InterruptedException, TTransportException, ConfigurationException, IOException {
		adminTemplate.createTable(true, CqlIdentifier.cqlId(DATA_TABLE_NAME), Book.class, new HashMap<String, Object>());
	}

	@Test
	public void whenSavingBook_thenAvailableOnRetrieval() {
		final Book javaBook = new Book(UUIDs.timeBased(), "Head First Java", "O'Reilly Media", ImmutableSet.of("Computer", "Software"));
		bookRepository.saveAll(ImmutableSet.of(javaBook));
		final Iterable<Book> books = bookRepository.findByTitleAndPublisher("Head First Java", "O'Reilly Media");
		assertEquals(javaBook.getId(), books.iterator().next().getId());
	}

	@Test
	public void whenUpdatingBooks_thenAvailableOnRetrieval() {
		final Book javaBook = new Book(UUIDs.timeBased(), "Head First Java", "O'Reilly Media", ImmutableSet.of("Computer", "Software"));
		bookRepository.saveAll(ImmutableSet.of(javaBook));
		final Iterable<Book> books = bookRepository.findByTitleAndPublisher("Head First Java", "O'Reilly Media");
		javaBook.setTitle("Head First Java Second Edition");
		bookRepository.saveAll(ImmutableSet.of(javaBook));
		final Iterable<Book> updateBooks = bookRepository.findByTitleAndPublisher("Head First Java Second Edition", "O'Reilly Media");
		assertEquals(javaBook.getTitle(), updateBooks.iterator().next().getTitle());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void whenDeletingExistingBooks_thenNotAvailableOnRetrieval() {
		final Book javaBook = new Book(UUIDs.timeBased(), "Head First Java", "O'Reilly Media", ImmutableSet.of("Computer", "Software"));
		bookRepository.saveAll(ImmutableSet.of(javaBook));
		bookRepository.delete(javaBook);
		final Iterable<Book> books = bookRepository.findByTitleAndPublisher("Head First Java", "O'Reilly Media");
		assertNotEquals(javaBook.getId(), books.iterator().next().getId());
	}

	@Test
	public void whenSavingBooks_thenAllShouldAvailableOnRetrieval() {
		final Book javaBook = new Book(UUIDs.timeBased(), "Head First Java", "O'Reilly Media", ImmutableSet.of("Computer", "Software"));
		final Book dPatternBook = new Book(UUIDs.timeBased(), "Head Design Patterns", "O'Reilly Media", ImmutableSet.of("Computer", "Software"));
		bookRepository.saveAll(ImmutableSet.of(javaBook));
		bookRepository.saveAll(ImmutableSet.of(dPatternBook));
		final Iterable<Book> books = bookRepository.findAll();
		int bookCount = 0;
		for (final Book book : books) {
			bookCount++;
		}
		assertEquals(bookCount, 2);
	}

	@After
	public void dropTable() {
		adminTemplate.dropTable(CqlIdentifier.cqlId(DATA_TABLE_NAME));
	}

	@AfterClass
	public static void stopCassandraEmbedded() {
		EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
	}

}