package com.nsrp.challenge.repository;

import com.nsrp.challenge.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE UPPER(c.email) = UPPER(:email)")
    Optional<Cliente> findByEmail(@Param("email") String email);
}