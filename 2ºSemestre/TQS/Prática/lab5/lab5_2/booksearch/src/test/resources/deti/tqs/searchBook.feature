@book_search
Feature: Book search

  Background: A platform to search for books
    a platform that allows a user to search for books

  Scenario: Add and remove a book
    Given a book with the title 'Doctor Sleep', written by 'Stephen King', published in 2010-05-12
    Then a book with the title 'Doctor Sleep', written by 'Stephen king', published in 2010-05-12 should be removed

  Scenario: Search for a book by author
    Given a book with the title 'Doctor Sleep', written by 'Stephen King', published in 2010-05-12
    And another book with the title 'The Shining', written by 'Stephen King', published in 1977-01-28
    And another book with the title 'The Brothers Kasamarov', written by 'Fyodor Dostoevsky', published in 1879-01-05
    And another book with the title 'The Hypnotist', written by 'Lars Kepler', published in 2009-01-01
    When a user searchs for books by 'Stephen King'
    Then it should find 2 books
    And book 1 should have the title 'Doctor Sleep' 
    And book 2 should have the title 'The Shining'

  Scenario: search for a book by publication year
    Given a book with the title 'The Hypnotist', written by 'Lars Kepler', published in 2009-01-01
    And another book with the title 'The Brothers Kasamarov', written by 'Fyodor Dostoevsky', published in 1879-01-05
    When a user searchs for books published between 2008-01-02 and 2016-12-31
    Then it should find 1 books
    And book 1 should have the title 'The Hypnotist'

  Scenario: search for a book by title
    Given a book with the title 'The Hypnotist', written by 'Lars Kepler', published in 2009-01-01
    And another book with the title 'The Brothers Kasamarov', written by 'Fyodor Dostoevsky', published in 1879-01-05
    And another book with the title 'Time Ages in a Hurry', written by 'Antonio Tabucchi', published in 2015-05-14
    When a user searchs for books with the title 'The Hypnotist'
    Then it should find 1 books

  Scenario: Number of books found by author map
    Given a user has the following books in a map in my store
      | title                  | author                 |
      | The Spider             | Lars Kepler            |
      | Angels and Demons      | Dan Brown              |
      | Behold of Life         | Christina Jahni        |
      | Dancing by the River   | Joseph Gorsh           |
      | The SandMan            | Lars Kepler            |
    When a user searchs for books by 'Lars Kepler'
    Then it should find 2 books

    