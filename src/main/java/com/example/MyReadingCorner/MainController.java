package com.example.MyReadingCorner;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/books")

public class MainController {
    @Autowired

    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/author")
    public List<Book> getByAuthor(@RequestParam (name = "author") String author){
        return (List<Book>) bookRepository.findByAuthor(author);
    }

    @GetMapping("/title")
    public List<Book> getByTitle(@RequestParam (name = "title") String title){
        return (List<Book>) bookRepository.findByTitle(title);
    }

    @GetMapping("/titleandauthor")
    public Book getByTitleAndAuthor(@RequestParam (name = "title") String title, @RequestParam(name = "author") String author){
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        if (bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Book with the same name and author already exists.");
        }
        if(book.getTitle() == "" || book.getAuthor() == ""){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please complete all the fields.");
        }
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public Book updateBookStatus(@PathVariable Integer id, @RequestParam(name = "status") String status) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Book book = optionalBook.get();
        book.setStatus(status);
        return bookRepository.save(book);
    }

    @DeleteMapping()
    public Book deleteBook(@RequestParam(name = "id", defaultValue = "0") Integer id, @RequestParam ( name = "title", required = false) String title, @RequestParam ( name = "author", required = false) String author){
        Book deleteBook ;
        if(id == 0){
            if(bookRepository.existsByTitleAndAuthor(title, author) == false){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }else{
               deleteBook =  bookRepository.findByTitleAndAuthor(title, author);
               bookRepository.deleteById(deleteBook.getId());
               return deleteBook;
            }
        }else{
             deleteBook = bookRepository.findById(id).get();
             bookRepository.deleteById(id);
             return deleteBook;
        }
        }
       
    }

