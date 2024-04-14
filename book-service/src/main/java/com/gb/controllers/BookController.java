package com.gb.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gb.model.Book;
import com.gb.services.BookService;
import org.springframework.ui.Model;



@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService service;

    @PostConstruct
    public void generateReaders() {
        for (int i = 0; i < 15; i++) {
            Book book = new Book();
            book.setName("Book: " + i);
            book.setDate(i);
            createBook(book);
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
//        System.out.println(book.getName() + " " + book.getDate());
        Book createdBook = service.createBook(book);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdBook.getId())
//                .toUri();
        return ResponseEntity.ok().body(createdBook);
    }


    @GetMapping()
    public ResponseEntity<List<Book>> getAllBook(Model model){
        return ResponseEntity.ok().body(service.getBooks());
    }

    @GetMapping("{name}")
    public ResponseEntity<Book> getName(@RequestParam String name) {
        return ResponseEntity.ok().body(service.getByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getId(@RequestParam UUID id) {
        return ResponseEntity.ok().body(service.getById(id));
    }


    @PutMapping()
    public ResponseEntity<Book> getUpdate(@RequestParam UUID id, @RequestParam Book book) {
        return ResponseEntity.ok().body(service.updateBook(id, book));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@RequestParam UUID id) {
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("random")
    public Book getById(){
        Random random = new Random();
        List<Book> books = service.getBooks();

        return books.get(random.nextInt(books.size()));
    }
}
