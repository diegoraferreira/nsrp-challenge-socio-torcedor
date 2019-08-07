package com.nsrp.challenge.service;

import com.nsrp.challenge.domain.Time;
import com.nsrp.challenge.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Transactional
    public Time save(String time) {
        Time timeDoCoracao = new Time(time);
        return this.timeRepository.save(timeDoCoracao);
    }

    @Transactional(readOnly = true)
    public Optional<Time> findByNome(String nome) {
        return this.timeRepository.findByNome(nome);
    }

    @Transactional
    public Time findOrCreateTimeDoCoracao(String timeDoCoracao) {
        Optional<Time> timeDoCoracaoOptional = timeRepository.findByNome(timeDoCoracao);
        if (timeDoCoracaoOptional.isPresent()) {
            return timeDoCoracaoOptional.get();
        } else {
            return this.save(timeDoCoracao);
        }
    }
}