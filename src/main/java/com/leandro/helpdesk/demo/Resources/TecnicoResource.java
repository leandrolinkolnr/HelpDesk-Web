package com.leandro.helpdesk.demo.Resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.helpdesk.demo.Services.TecnicoService;
import com.leandro.helpdesk.demo.domain.Tecnico;
import com.leandro.helpdesk.demo.domain.dtos.TecnicoDto;

@RestController  // controlador Rest
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
}
