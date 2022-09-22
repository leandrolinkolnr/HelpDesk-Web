package com.leandro.helpdesk.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leandro.helpdesk.demo.Services.DBService;

@Configuration
@Profile("Test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean   // de forma automatica vai chamar o metodo quando o perfil teste estiver ativo  
    public void instaciaDB(){
        this.dbService.instanciaDB();   // metodo da classe DBService
    } 
    
}
