package com.leandro.helpdesk.demo.domain.enums;

public enum Prioridade {
    
    BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

    private Integer codigo;
    private String descrição;

    private Prioridade(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descrição = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }
    
    public String getDescrição() {
        return descrição;
    }
 
    public static Perfil toEnum(Integer cod){
        if(cod == null) return null;

        for(Perfil x: Perfil.values()){
            if(cod.equals(x.getCodigo())) return x;
        }

        throw new IllegalArgumentException("Prioridade Invalida"); 
    }
}
