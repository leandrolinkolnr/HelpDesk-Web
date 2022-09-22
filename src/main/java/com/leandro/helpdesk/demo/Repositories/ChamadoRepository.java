package com.leandro.helpdesk.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.helpdesk.demo.domain.Chamado;

// Jpa repository vai fazer a persistencia das informações no banco
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    
}
