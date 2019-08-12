package com.nsrp.challenge.service;

import com.nsrp.challenge.model.TimeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class TimeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nsrp.challenge.campanha.url}")
    private String baseUrl;

    @Value("${nsrp.challenge.campanha.time.list}")
    private String listTimeUrl;

    @Transactional(readOnly = true)
    public TimeModel findByNome(String nome) {
        try {
            ResponseEntity<TimeModel> response = restTemplate.getForEntity(baseUrl.concat(listTimeUrl), TimeModel.class, nome);
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
}