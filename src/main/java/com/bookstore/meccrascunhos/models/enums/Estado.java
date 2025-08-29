package com.bookstore.meccrascunhos.models.enums;

import com.bookstore.meccrascunhos.exceptions.ResourceNotFoundException;

public enum Estado {
    AC(1, "Acre"),
    AL(2, "Alagoas"),
    AP(3, "Amapá"),
    AM(4, "Amazonas"),
    BA(5, "Bahia"),
    CE(6, "Ceará"),
    ES(7, "Espírito Santo"),
    GO(8, "Goiás"),
    DF(9, "Distrito Federal"),
    MA(10, "Maranhão"),
    MT(11, "Mato Grosso"),
    MS(12, "Mato Grosso do Sul"),
    MG(13, "Minas Gerais"),
    PA(14, "Pará"),
    PB(15, "Paraíba"),
    PR(16, "Paraná"),
    PE(17, "Pernambuco"),
    PI(18, "Piauí"),
    RJ(19, "Rio de Janeiro"),
    RN(20, "Rio Grande do Norte"),
    RS(21, "Rio Grande do Sul"),
    RO(22, "Rondônia"),
    RR(23, "Roraima"),
    SC(24, "Santa Catarina"),
    SP(25, "São Paulo"),
    SE(26, "Sergipe"),
    TO(28, "Tocantins");

    private final int code;
    private final String nome;

    Estado(int code, String nome) {
        this.code = code;
        this.nome = nome;
    }

    public int getCode() {
        return code;
    }

    public String getNome() {
        return nome;
    }

    public static final Estado valueOf(int code) {
        for (Estado e : Estado.values()) {
            if (code == e.getCode()) return e;
        }
        throw new ResourceNotFoundException("Estado de código " + code + " não encontrado.");
    }

    @Override
    public String toString() {
        return "Estado[" +
                "code=" + code +
                ", nome='" + nome + '\'' +
                ']';
    }
}
