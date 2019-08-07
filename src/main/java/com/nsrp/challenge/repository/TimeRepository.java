package com.nsrp.challenge.repository;

import com.nsrp.challenge.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

    @Query("SELECT time FROM Time time WHERE UPPER(NOME) = UPPER(:nome)")
    Optional<Time> findByNome(@Param("nome") String nome);
}