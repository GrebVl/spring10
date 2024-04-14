package com.gb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private int date;

    public Book(String name, int date){
        super();
        this.name = name;
        this.date = date;
    }

    public Book(){
        this.id = UUID.randomUUID();
    }

}
