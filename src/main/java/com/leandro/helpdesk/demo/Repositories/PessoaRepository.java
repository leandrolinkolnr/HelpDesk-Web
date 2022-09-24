package com.leandro.helpdesk.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.helpdesk.demo.domain.Pessoa;

// Jpa repository vai fazer a persistencia das informações no banco
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    
    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);
}
