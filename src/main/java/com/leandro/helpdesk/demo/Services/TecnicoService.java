package com.leandro.helpdesk.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.helpdesk.demo.Repositories.TecnicoRepository;
import com.leandro.helpdesk.demo.Services.Exceptions.ObjectNotFoundException;
import com.leandro.helpdesk.demo.domain.Tecnico;

// pra poder ingetar
@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }
    
}
