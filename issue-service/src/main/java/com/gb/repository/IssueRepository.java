package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.gb.model.Issue;

import java.util.UUID;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
}
