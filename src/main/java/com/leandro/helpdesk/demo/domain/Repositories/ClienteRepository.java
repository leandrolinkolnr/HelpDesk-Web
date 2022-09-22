package com.leandro.helpdesk.demo.domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.helpdesk.demo.domain.Cliente;

// Jpa repository vai fazer a persistencia das informações no banco
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
