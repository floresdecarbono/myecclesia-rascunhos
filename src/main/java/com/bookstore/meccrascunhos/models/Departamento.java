package com.bookstore.meccrascunhos.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_DEPARTAMENTOS")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;
    private Integer quantMembros;

    private Set<Membro> lideres = new HashSet<>();
    private Set<Evento> eventos = new LinkedHashSet<>();
    private Set<Membro> membros = new HashSet<>();

    public Departamento() {}

    public Departamento(UUID id, String nome, Integer quantMembros, Set<Membro> lideres, Set<Evento> eventos, Set<Membro> membros) {
        this.id = id;
        this.nome = nome;
        this.quantMembros = quantMembros;
        this.lideres = lideres;
        this.eventos = eventos;
        this.membros = membros;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantMembros() {
        return quantMembros;
    }

    public void setQuantMembros(Integer quantMembros) {
        this.quantMembros = quantMembros;
    }

    public Set<Membro> getLideres() {
        return lideres;
    }

    public void setLideres(Set<Membro> lideres) {
        this.lideres = lideres;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

    public Set<Membro> getMembros() {
        return membros;
    }

    public void setMembros(Set<Membro> membros) {
        this.membros = membros;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return "Departamento[" +
                "id='" + id + '\'' +
                "nome='" + nome + '\'' +
                ", quantMembros=" + quantMembros +
                ", lideres=" + lideres +
                ", eventos=" + eventos +
                ", membros=" + membros +
                ']';
    }

}
