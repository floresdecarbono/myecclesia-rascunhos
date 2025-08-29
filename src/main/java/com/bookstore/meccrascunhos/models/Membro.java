package com.bookstore.meccrascunhos.models;

import com.bookstore.meccrascunhos.models.enums.Cargo;
import com.bookstore.meccrascunhos.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_MEMBROS")
public class Membro extends RepresentationModel<Membro> implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String CPF;

    @Column(nullable = false)
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String telefone;

    @Column(name = "cargo", nullable = false)
    @JsonProperty(value = "cargo")
    private Integer cargoCode;

    @Column(name = "status", nullable = false)
    private Integer statusCode;

    public Membro() {}

    public Membro(UUID id, String CPF, String nome, LocalDate dataNascimento, String telefone, Cargo cargo, Status status) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;

        setCargoCode(cargo);
        setStatusCode(status);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cargo getCargoCode() {
        return Cargo.valueOfCode(cargoCode);
    }

    public void setCargoCode(Cargo cargo) {
        this.cargoCode = cargo.getCode();
    }

    public Status getStatusCode() {
        return Status.valueOfCode(statusCode);
    }

    public void setStatusCode(Status statusCode) {
        this.statusCode = statusCode.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Membro membro = (Membro) o;
        return Objects.equals(CPF, membro.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CPF);
    }

    @Override
    public String toString() {
        return "Membro[" +
                "id='" + id + '\'' +
                "CPF='" + CPF + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento'" + dataNascimento + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cargo='" + cargoCode + '\'' +
                ", status='" + statusCode + '\'' +
                ']';
    }

}
