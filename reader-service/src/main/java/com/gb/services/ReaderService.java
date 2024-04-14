package com.gb.services;

import com.gb.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;

import org.hibernate.event.spi.RefreshEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gb.model.*;


import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
@Service
public class ReaderService {

    public static final String NOT_FOUND_MESSAGE = "Не удалось найти читателя с заданным значением = ";


    private final ReaderRepository repository;

    public List<Reader> getReaders() {
        Iterable<Reader> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }


    public Reader getById(UUID id){
        Reader reader = repository.findById(id).orElse(null);
        if (reader == null){
            throwNotFoundExceptionById(id);
        }
        return reader;
    }

    public void deleteReader(UUID id){
        checkExistsById(id);
        repository.delete(getById(id));
    }

    public Reader updateReader(UUID id, Reader reader) {
        checkExistsById(id);
        reader.setId(id);
        return repository.save(reader);
    }

    public Reader createReader(Reader reader){
        return repository.save(reader);
    }

    private void checkExistsById(UUID id){
        if(!repository.existsById(id)){
            throwNotFoundExceptionById(id);
        }
    }

    private void throwNotFoundExceptionById(UUID id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + id);
    }

    private void throwNotFoundExceptionByName(String name){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE + name);
    }

}
