package deti.tqs.books;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import deti.tqs.books.models.Book;
import deti.tqs.books.repository.BookRepository;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BooksApplicationTests {

	@SuppressWarnings("rawtypes")
	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:13.2")
		.withDatabaseName("books")
		.withUsername("username")
		.withPassword("password");

	@Autowired
	private BookRepository bookRepository;

	@DynamicPropertySource
	public static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	@Order(1)
	public void testCreateBook() {
		Book book = new Book("", null, null, null, 0, 0)
	}



}
