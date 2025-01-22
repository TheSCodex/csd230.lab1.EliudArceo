package csd230.lab1.repositories;

import csd230.lab1.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom query to search by ISBN
    List<Book> findByIsbn(String isbn);

    // Custom query to search by id
    Book findById(long id);

    // Custom query to search by title
    List<Book> findByTitle(String title);

    // Custom query to search by author
    List<Book> findByAuthor(String author);

    // Custom query to search by title containing a specific text
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:text%")
    List<Book> findByTitleContaining(@Param("text") String text);

    // Custom query to search by author containing a specific string
    @Query("SELECT b FROM Book b WHERE b.author LIKE %:text%")
    List<Book> findByAuthorContaining(@Param("text") String text);

}
