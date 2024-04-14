package com.gb.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookRequest {
    private UUID id;
    private String bookName;
}
