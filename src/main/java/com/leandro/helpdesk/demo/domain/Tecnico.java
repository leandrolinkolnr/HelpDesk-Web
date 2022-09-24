package com.leandro.helpdesk.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leandro.helpdesk.demo.domain.dtos.TecnicoDto;
import com.leandro.helpdesk.demo.domain.enums.Perfil;

// Avisando ao JPA que pessoa é uma entedidade, queo criar um tabela no bd
@Entity
public class Tecnico extends Pessoa  {

    private static final long serialVersionUID = 1L;

    @JsonIgnore     // Não passar a lista de chamado que tem o tecnico, criando loop
    @OneToMany(mappedBy = "tecnico")  // Um para muitos   (Um tecnico para muitos chamados)
    // foram mapeados por tecnico


    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(TecnicoDto obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();

        // Tem que pegar o codigo de cada perfil
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
    
}
