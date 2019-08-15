package com.nsrp.challenge.service;

import com.nsrp.challenge.model.CampanhaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CampanhaService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nsrp.challenge.campanha.url}")
    private String baseUrl;

    @Value("${nsrp.challenge.campanha.timedocoracao}")
    private String campanhaUrl;

    public List<CampanhaModel> findCampanhasByTimeDoCoracao(String timeDoCoracao) {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("timeDoCoracao", timeDoCoracao);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl.concat(campanhaUrl));
        final ResponseEntity<List<CampanhaModel>> response = restTemplate.exchange(
                builder.buildAndExpand(uriParams).toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CampanhaModel>>() {
                });
        return response.getBody();
    }
}