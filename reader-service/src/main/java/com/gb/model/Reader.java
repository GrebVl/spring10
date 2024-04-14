package com.gb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "readers")
public class Reader {

    @Id
    private UUID id;

//    @Column(name = "login")
//    private String login;
//
//    @Column(name = "password")
//    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

//    @Column(name = "role")
//    private String role;

    public Reader(String name, int age){
        super();
        this.name = name;
        this.age = age;
    }

    public Reader(){
        this.id = UUID.randomUUID();
    }

}
