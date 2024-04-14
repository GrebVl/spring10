package com.gb.dto;

import com.gb.model.BookIssue;
import com.gb.model.ReaderIssue;
import lombok.Data;

import java.util.UUID;

@Data
public class IssueRequest {
    private UUID bookIssue;
    private UUID readerIssue;
}
