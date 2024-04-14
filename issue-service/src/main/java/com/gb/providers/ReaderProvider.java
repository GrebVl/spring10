package com.gb.providers;

import com.gb.model.ReaderIssue;
import com.netflix.discovery.EurekaClient;
import jakarta.ws.rs.ext.Provider;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
public class ReaderProvider {
    private final WebClient webClient;

    public ReaderProvider(EurekaClient eurekaClient, ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public UUID getRandomReader() {
        ReaderIssue randomReader = webClient.get()
                .uri("http://reader-service/reader/random")
                .retrieve()
                .bodyToMono(ReaderIssue.class)
                .block();

        return randomReader.getId();
    }

    public boolean checkReaderExists(UUID id){
        String addres = "http://reader-service/reader/" + id.toString().replace("-", "");

        ReaderIssue reader = webClient.get()
                .uri(addres)
                .retrieve()
                .bodyToMono(ReaderIssue.class)
                .block();
        if(reader == null){
            return false;
        }
        return true;
    }

    public UUID findById(UUID id){
        String addres = "http://reader-service/reader/" + id;

        ReaderIssue reader = webClient.get()
                .uri(addres)
                .retrieve()
                .bodyToMono(ReaderIssue.class)
                .block();
        if(reader == null){
            return null;
        }
        return reader.getId();
    }
}
