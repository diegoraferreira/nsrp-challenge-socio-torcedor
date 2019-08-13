package com.nsrp.challenge.service;

import com.nsrp.challenge.model.TimeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class TimeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nsrp.challenge.campanha.url}")
    private String baseUrl;

    @Value("${nsrp.challenge.campanha.time.list.nome}")
    private String listTimeNome;

    @Value("${nsrp.challenge.campanha.time.list.id}")
    private String listTimeId;

    public TimeModel findByNome(String nome) {
        try {
            ResponseEntity<TimeModel> response = restTemplate.getForEntity(baseUrl.concat(listTimeNome), TimeModel.class, nome);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                String message = "O time com nome '%s' não foi encontrado";
                throw new RuntimeException(String.format(message, nome), e);
            } else {
                throw e;
            }
        }
    }

    public TimeModel findById(Long id) {
        try {
            ResponseEntity<TimeModel> response = restTemplate.getForEntity(baseUrl.concat(listTimeId), TimeModel.class, id);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                String message = "O time com id '%s' não foi encontrado";
                throw new RuntimeException(String.format(message, id), e);
            } else {
                throw e;
            }
        }
    }
}