package com.bookstore.meccrascunhos.models.dtos;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class LocalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String descricao;
    private String numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private Integer estado;

    public LocalDTO() {}

    public LocalDTO(UUID id, String descricao, String numero, String logradouro, String bairro, String cidade, Integer estado) {
        this.id = id;
        this.descricao = descricao;
        this.numero = numero;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocalDTO localDTO = (LocalDTO) o;
        return Objects.equals(id, localDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LocalDTO[" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", numero='" + numero + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado=" + estado +
                ']';
    }
}
