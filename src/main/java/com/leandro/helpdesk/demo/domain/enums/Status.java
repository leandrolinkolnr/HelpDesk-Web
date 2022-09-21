package com.leandro.helpdesk.demo.domain.enums;

public enum Status {
    
    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descrição;

    private Status(Integer codigo, String descricao){
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

        throw new IllegalArgumentException("Status Invalido"); 
    }
}
