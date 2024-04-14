package com.gb.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReaderRequest {
    private UUID id;
    private String readerName;
}
