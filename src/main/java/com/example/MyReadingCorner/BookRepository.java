package com.example.MyReadingCorner;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.MyReadingCorner.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
    List<Book> findByStatus(String status);
}
