package com.leandro.helpdesk.demo.Resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leandro.helpdesk.demo.Services.TecnicoService;
import com.leandro.helpdesk.demo.domain.Tecnico;
import com.leandro.helpdesk.demo.domain.dtos.TecnicoDto;

@RestController  // controlador Rest - Onde recebe as requisições
@RequestMapping(value="/tecnicos")          // qual o caminho p/ tecnicos
public class TecnicoResource {
    
    @Autowired
    private TecnicoService service;

    // Quando passar /tecnicos/1 é pra buscar pelo ID 1
    @GetMapping(value="/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
             Tecnico obj = service.findById(id);

             // vai construir o dto passando o obj que é do tipo tecnico
             return ResponseEntity.ok().body(new TecnicoDto(obj));
        
    }

    // Retornar a lista de todos os tecnicos
    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll(){
        List<Tecnico> list = service.findAll();

        // convertendo tecnico em tecnico dto para retornar
        List<TecnicoDto> listDto = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    // Para criar, ele precisa receber os parametros de um tecnico
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto objDto){
        Tecnico newObj = service.create(objDto);     // cria um metodo create no tecnicoService
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
        buildAndExpand(newObj.getId()).toUri();     // retornar 
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
     public ResponseEntity<TecnicoDto> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDto objDto){
        Tecnico obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
     }

}
