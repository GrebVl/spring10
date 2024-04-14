package com.gb.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "issues")
public class Issue {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "Reader")
    private UUID readerIssue;

    @Column(name = "Book")
    private UUID bookIssue;

    @Column(name = "issuedAt")
    private LocalDateTime issuedAt;

    @Column(name = "returnedAt")
    private LocalDateTime returnedAt;

    public Issue(UUID book, UUID reader){
        super();
        this.readerIssue = reader;
        this.bookIssue = book;
        this.issuedAt = LocalDateTime.now();
    }

    public Issue(){
        this.id = UUID.randomUUID();
    }

}
