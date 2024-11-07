package com.backend.chopper.repository;

import com.backend.chopper.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByEmail(String email);
}
