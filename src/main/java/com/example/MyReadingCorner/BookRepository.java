package com.example.MyReadingCorner;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.MyReadingCorner.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
    List<Book> findByStatus(String status);
    boolean existsByTitleAndAuthor(String title, String author);
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    Book findByTitleAndAuthor(String title, String author);
    Book findByIdAndTitle(Integer id, String title);
    Book findByIdAndAuthor(Integer id, String author);
    Book findByIdAndTitleAndAuthor(Integer id, String title, String author);
}
