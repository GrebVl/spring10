package ru.gb.services;

import ru.gb.providers.BookProvider;
import ru.gb.providers.ReaderProvider;
import ru.gb.model.Issue;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.gb.repository.IssueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class IssueService{

    public static final String NOT_FOUND_MESSAGE = "Не удалось найти запись о выдаче с значение ";

    private final IssueRepository issueRepository;
    private final BookProvider bookProvider;
    private final ReaderProvider readerProvider;

    public UUID randomReader(){
        return readerProvider.getRandomReader();
    }

    public UUID randomBook(){
        return bookProvider.getRandomBook();
    }

    public Issue findById(UUID id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        if (issue == null) {
            throwNotFoundExceptionById(id);
        }
        return issue;
    }

    public void deleteById(UUID id) {
        checkExistsById(id);
        issueRepository.deleteById(id);
    }

    public Issue saveIssue(Issue issue) {

        //checkBookExists(issue);
        //checkReaderExists(issue);

        Issue savedIssue = createIssueAndSave(issue);
        //fillIssue(savedIssue);

        return savedIssue;
    }

    public Issue returnBook(UUID id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        if (issue == null) {
            throwNotFoundExceptionById(id);
        }

        if (issue.getReturnedAt() == null) {
            issue.setReturnedAt(LocalDateTime.now());
            return issueRepository.save(issue);
        }
        return issue;
    }

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    private void fillIssue(Issue issue) {
        issue.setBookIssue(bookProvider.findById(issue.getBookIssue()));
        issue.setReaderIssue(readerProvider.findById(issue.getReaderIssue()));
    }

    private Issue createIssueAndSave(Issue issue) {
        return issueRepository.save(issue);
    }

    private void checkExistsById(UUID id) {
        if (!issueRepository.existsById(id)) {
            throwNotFoundExceptionById(id);
        }
    }


    private void checkReaderExists(Issue issue) {
        if (issue.getReaderIssue() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "не указано значение для поля reader");
        }
        if (!readerProvider.checkReaderExists(issue.getReaderIssue())) {
            throwNotFoundExceptionById(issue.getReaderIssue(), IssueService.NOT_FOUND_MESSAGE);
        }
    }

    private void checkBookExists(Issue issue) {
        if (issue.getBookIssue() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "не указано значение для поля book");
        }
        if (!bookProvider.checkBookExists(issue.getBookIssue())) {
            throwNotFoundExceptionById(issue.getBookIssue(), IssueService.NOT_FOUND_MESSAGE);
        }
    }

    private void throwNotFoundExceptionById(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + id);
    }

    private void throwNotFoundExceptionById(UUID id, String message) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, message + id);
    }
}
