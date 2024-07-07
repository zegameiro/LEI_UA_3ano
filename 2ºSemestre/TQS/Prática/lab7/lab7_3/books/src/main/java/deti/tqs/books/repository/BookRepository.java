package deti.tqs.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import deti.tqs.books.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

  Book findByTitle(String title);
  Book findByAuthor(String author);

}
