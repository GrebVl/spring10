package com.gb.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.model.Reader;


@Repository
public interface ReaderRepository extends JpaRepository<Reader, UUID> {
//    Optional<Reader> findByLogin(String login);
}
