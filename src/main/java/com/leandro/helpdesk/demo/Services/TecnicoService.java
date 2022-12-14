package com.leandro.helpdesk.demo.Services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.leandro.helpdesk.demo.Repositories.PessoaRepository;
import com.leandro.helpdesk.demo.Repositories.TecnicoRepository;
import com.leandro.helpdesk.demo.Services.Exceptions.ObjectNotFoundException;
import com.leandro.helpdesk.demo.domain.Pessoa;
import com.leandro.helpdesk.demo.domain.Tecnico;
import com.leandro.helpdesk.demo.domain.dtos.TecnicoDto;

// pra poder ingetar
@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDto objDto) {
        // se passar um id que ja tem o banco vai pensar que é um apdate e vai bagunçar
        objDto.setId(null);
        validaPorCpfEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDto objDto) {
        objDto.setId(id);    // pode passsar um id e ser outro
        Tecnico oldObj = findById(id);
        validaPorCpfEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj); 
     }

     public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Tecnico possui ordens de servico e nao pode ser deletado!");
        } 
        repository.deleteById(id);  // nao precisa de else
        
    }



    private void validaPorCpfEmail(TecnicoDto objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        // metodo pra saber se um opcional retornou um valido
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){     // existe e for diferente doq recebeu
            throw new DataIntegrityViolationException("Cpf ja cadastrado no sistema.");
        }  

        obj = pessoaRepository.findByEmail(objDto.getEmail());

        if(obj.isPresent() && obj.get().getId() != objDto.getId()){     // existe e for diferente doq recebeu
            throw new DataIntegrityViolationException("Email ja cadastrado no sistema.");
        }  
    }


    
}
