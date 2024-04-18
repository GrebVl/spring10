package ru.gb.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class IssueRequest {
    private UUID bookIssue;
    private UUID readerIssue;
}
