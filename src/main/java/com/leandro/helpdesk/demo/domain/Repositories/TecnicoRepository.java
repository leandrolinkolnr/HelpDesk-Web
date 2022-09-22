package com.leandro.helpdesk.demo.domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.helpdesk.demo.domain.Tecnico;

// Jpa repository vai fazer a persistencia das informações no banco
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    
}
