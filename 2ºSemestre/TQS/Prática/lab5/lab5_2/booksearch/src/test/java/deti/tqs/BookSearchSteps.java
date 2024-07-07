package deti.tqs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import deti.tqs.models.Book;

public class BookSearchSteps {

  Library library = new Library();
  List<Book> results = new ArrayList<Book>();  

  @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
  public LocalDateTime isodate(final String year, final String month, final String day) {
    return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
  }

  @Given("a book with the title {string}, written by {string}, published in {isodate}")
  public void addNewBook(final String title, final String author, final LocalDateTime publishDate) {
    library.addBook(new Book(title, author, publishDate));
  }

  @Given("another book with the title {string}, written by {string}, published in {isodate}")
  public void addNewBook2(final String title, final String author, LocalDateTime publishDate) {
    library.addBook(new Book(title, author, publishDate));
  }

  @Given("a user has the following books in a map in my store")
  public void iHaveTheFollowingBooksInTheStoreByMap(DataTable table) {
    List<Map<String, String>> rows = table.asMaps(String.class, String.class);

    for (Map<String, String> columns : rows) {
        library.addBook(new Book(columns.get("title"), columns.get("author")));
    }
  }

  @When("a user searchs for books published between {isodate} and {isodate}")
  public void searchBookByPublishedDate(final LocalDateTime from, final LocalDateTime to) {
    results = library.findBooks(from, to);
  }

  @When("a user searchs for books by {string}")
  public void searchBookByAuthor(final String author) {
    results = library.findBooksByAuthor(author);
  }

  @When("a user searchs for books with the title {string}")
  public void searchBookByTitle(final String title) {
    results.add(library.findBook(title));
  }

  @Then("it should find {int} books")
  public void verifyResultsSize(int num) {
    System.out.println(results.size());
    assertThat(results.size()).isEqualTo(num);
  }

  @Then("a book with the title {string}, written by {string}, published in {isodate} should be removed")
  public void verifyBookRemoved(final String title, final String author, final LocalDateTime publishDate) {
    Book book = new Book(title, author, publishDate);
    library.removeBook(book);
  }

  @Then("book {int} should have the title {string}")
  public void verifyBookTitle(int index, String title) {
    assertEquals(results.get(index-1).getTitle(), title);
  }
}
