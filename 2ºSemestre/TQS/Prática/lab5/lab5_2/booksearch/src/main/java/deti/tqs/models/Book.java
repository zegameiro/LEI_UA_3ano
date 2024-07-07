package deti.tqs.models;

import java.time.LocalDateTime;

public class Book {
  private final String title;
  private final String author;
  private final LocalDateTime publishDate;

  public Book(String title, String author, LocalDateTime publishDate) {
    this.title = title;
    this.author = author;
    this.publishDate = publishDate;
  }

  public  Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.publishDate = LocalDateTime.now();
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public LocalDateTime getPublishDate() {
    return publishDate;
  }

  @Override
  public String toString() {
    return "Book{" +
      "title='" + title + '\'' +
      ", author='" + author + '\'' +
      ", publishDate=" + publishDate +
      '}';
  }
}
