package com.gb.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ReaderIssue {
    private UUID id;
    private String name;
    private int age;
}
