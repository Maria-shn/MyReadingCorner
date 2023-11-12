package com.example.MyReadingCorner;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/books")

public class MainController {
    @Autowired

    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        System.out.println("it should work");
        return bookRepository.save(book);
    }

    @PutMapping("/{id}/status")
    public Book updateBookStatus(@PathVariable Integer id, @RequestParam String status) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        book.setStatus(status);
        return bookRepository.save(book);
    }
}