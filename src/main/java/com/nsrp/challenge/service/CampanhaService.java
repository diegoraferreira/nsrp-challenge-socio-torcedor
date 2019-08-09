package com.nsrp.challenge.service;

import com.nsrp.challenge.model.CampanhaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CampanhaService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("nsrp.challenge.campanha.campanha.url")
    private String url;

    public List<CampanhaModel> findCampanhasByTimeDoCoracao(Long idTime) {
        final ResponseEntity<List<CampanhaModel>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CampanhaModel>>() {
                });
        return response.getBody();
    }
}