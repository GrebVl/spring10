package com.gb.controllers;

import ru.gb.controllers.testOptionsClasses.JUnitSpringBootBase;
import ru.gb.controllers.testOptionsClasses.TestConfig;
import com.gb.model.Book;
import com.gb.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TestConfig.class)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    private BookRepository bookRepository;


    @BeforeAll
    void generateBook(){
        bookRepository.save(new Book("Book 1", 1980));
        bookRepository.save(new Book("Book 2", 1981));
        bookRepository.save(new Book("Book 3", 1982));
    }


    @Test
    void createBook() {
        Book book1 = bookRepository.save(new Book("Book 4", 1980));

        Book bookTest = webTestClient.get()
                .uri("book/" + book1.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertNotNull(bookTest);
        Assertions.assertEquals(book1.getId(), bookTest.getId());
        Assertions.assertEquals(book1.getName(), bookTest.getName());
        Assertions.assertEquals(book1.getDate(), bookTest.getDate());
    }

/**
    @Test
    void getAllBook() {

    }

    @Test
    void getName() {
    }

    @Test
    void getId() {
    }

    @Test
    void getUpdate() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void getById() {

    }
    */
}