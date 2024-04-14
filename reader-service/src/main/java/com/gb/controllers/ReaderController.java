package com.gb.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gb.model.*;
import com.gb.services.ReaderService;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReaderController {

    @Autowired
    private ReaderService service;

    @PostConstruct
    public void generateReaders() {
        for (int i = 0; i < 15; i++) {
            Reader reader = new Reader();
            reader.setName("reader: " + i);
            reader.setAge(i);
            createReader(reader);
        }
    }

    @GetMapping("any")
    public String any() {
        return "any";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("readers")
    public String reader() {
        return "readers";
    }

    @GetMapping("auth")
    public String auth() {
        return "auth";
    }


    @GetMapping("{id}")
    public ResponseEntity<Reader> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Reader>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getReaders());
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader){
        Reader createdReader = service.createReader(reader);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdReader.getId())
//                .toUri();
        return ResponseEntity.ok().body(createdReader);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable UUID id){
        service.deleteReader(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Reader> updateReader(@PathVariable UUID id, @RequestBody Reader reader){
        return ResponseEntity.ok().body(service.updateReader(id, reader));
    }

    @GetMapping("random")
    public Reader getById(){
        Random random = new Random();
        List<Reader> readers = service.getReaders();

        return readers.get(random.nextInt(readers.size()));
    }


}
