package com.leandro.helpdesk.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leandro.helpdesk.demo.Services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    // Se la tiver create, ele pede pra criar as tabelas
    // La fica create so a primeira vez, depois muda pra none, se nao vai apagar tudo e recriar as tabelas.
    @Value("${spring.jpa.hibernate.dd1-auto}")
    private String value;

    @Bean   // de forma automatica vai chamar o metodo quando o perfil teste estiver ativo  
    public boolean instaciaDB(){
        if(value.equals("create")){         // Se tiver create, ele pede pra criar as tabelas
            this.dbService.instanciaDB();   // metodo da classe DBService
        }
        return false;
        
    } 
    
}
