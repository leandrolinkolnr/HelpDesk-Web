package com.leandro.helpdesk.demo.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.helpdesk.demo.Services.TecnicoService;
import com.leandro.helpdesk.demo.domain.Tecnico;

@RestController  // controlador Rest
@RequestMapping(value="/tecnicos")          // qual o caminho p/ tecnicos
public class TecnicoResource {
    
    @Autowired
    private TecnicoService service;

    // Quando passar /tecnicos/1 é pra buscar pelo ID 1
    @GetMapping(value="/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
             Tecnico obj = service.findById(id);
             return ResponseEntity.ok().body(obj);
        
    }
}
