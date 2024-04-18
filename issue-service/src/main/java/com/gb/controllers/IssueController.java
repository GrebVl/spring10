package ru.gb.controllers;

import ru.gb.services.IssueService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.gb.model.Issue;


@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;

    @PostConstruct
    public void generateIssue(){
         for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setReaderIssue(service.randomReader());
            issue.setBookIssue(service.randomBook());

            service.saveIssue(issue);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Issue> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Issue>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue){
        Issue createdIssue = service.saveIssue(issue);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdIssue.getId())
//                .toUri();
        return ResponseEntity.ok().body(createdIssue);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable UUID id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Issue> returnBook(@PathVariable UUID id){
        return ResponseEntity.ok().body(service.returnBook(id));
    }
}
