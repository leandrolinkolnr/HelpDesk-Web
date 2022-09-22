package com.leandro.helpdesk.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.leandro.helpdesk.demo.domain.enums.Perfil;

// Avisando ao JPA que pessoa é uma entedidade, queo criar um tabela no bd
@Entity
public class Cliente extends Pessoa {
    
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")  // Um para muitos   (Um tecnico para muitos chamados)
    // foram mapeados por tecnico
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
    
}
