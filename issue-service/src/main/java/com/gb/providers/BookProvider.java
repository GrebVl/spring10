package com.gb.providers;

import com.gb.model.BookIssue;
import com.netflix.discovery.EurekaClient;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

import jakarta.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BookProvider {
    private final WebClient webClient;

    public BookProvider(EurekaClient eurekaClient, ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public UUID getRandomBook() {
        BookIssue randomBook = webClient.get()
                .uri("http://BOOK-SERVICE/book/random")
                .retrieve()
                .bodyToMono(BookIssue.class)
                .block();

        return randomBook.getId();
    }

    public boolean checkBookExists(UUID id){
        String address = "http://BOOK-SERVICE/book/%" + id;

        BookIssue book = webClient.get()
                .uri(address)
                .retrieve()
                .bodyToMono(BookIssue.class)
                .block();
        if(book == null){
            return false;
        }
        return true;
    }

    public UUID findById(UUID id){
        String addres = "http://BOOK-SERVICE/book/" + id;
        BookIssue book = webClient.get()
                .uri(addres)
                .retrieve()
                .bodyToMono(BookIssue.class)
                .block();
        if(book == null){
            return null;
        }
        return book.getId();
    }

}
