package com.example.MyReadingCorner;
import org.springframework.data.repository.CrudRepository;
import com.example.MyReadingCorner.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
    
}
