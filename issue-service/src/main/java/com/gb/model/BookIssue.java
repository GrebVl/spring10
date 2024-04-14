package com.gb.model;

import lombok.Data;

import java.util.UUID;

@Data
public class BookIssue {
    private UUID id;
    private String name;
    private int data;
}
