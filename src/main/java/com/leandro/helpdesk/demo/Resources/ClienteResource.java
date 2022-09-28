package com.leandro.helpdesk.demo.Resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leandro.helpdesk.demo.Services.ClienteService;
import com.leandro.helpdesk.demo.domain.Cliente;
import com.leandro.helpdesk.demo.domain.dtos.ClienteDto;


@RestController  // controlador Rest - Onde recebe as requisições
@RequestMapping(value="/clientes")          // caminho p/ Cliente
public class ClienteResource {
    
    @Autowired
    private ClienteService service;

    // Quando passar /Cliente/1 é pra buscar pelo ID 1
    @GetMapping(value="/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
         Cliente obj = service.findById(id);

             // vai construir o dto passando o obj que é do tipo Cliente
             return ResponseEntity.ok().body(new ClienteDto(obj));
        
    }

    // Retornar a lista de todos os Cliente
    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
        List<Cliente> list = service.findAll();

        // convertendo Cliente em Cliente dto para retornar
        List<ClienteDto> listDto = list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    // Para criar, ele precisa receber os parametros de um Cliente
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objDto){
        Cliente newObj = service.create(objDto);     // cria um metodo create no ClienteService
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
        buildAndExpand(newObj.getId()).toUri();     // retornar 
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
     public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @Valid @RequestBody ClienteDto objDto){
        Cliente obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDto(obj));
     }

     @DeleteMapping(value = "/{id}")
     public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
     }
     }
     

    


