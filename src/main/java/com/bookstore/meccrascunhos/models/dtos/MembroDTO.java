package com.bookstore.meccrascunhos.models.dtos;

import com.bookstore.meccrascunhos.models.enums.Cargo;
import com.bookstore.meccrascunhos.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@JsonPropertyOrder({"id", "nome", "cpf", "dataDeNascimento", "cargo", "status"})
public class MembroDTO extends RepresentationModel<MembroDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private String CPF;

    private String nome;

    @JsonProperty(value = "dataDeNascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String telefone;

    @JsonProperty(value = "cargo")
    private String cargoName;

    @JsonProperty(value = "status")
    private String statusName;

    public MembroDTO() {}

    public MembroDTO(UUID id, String CPF, String nome, LocalDate dataNascimento, String telefone, Cargo cargo, Status status) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;

        setCargoName(cargo);
        setStatusName(status);
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

    public Cargo getCargoName() {
        return Cargo.valueOfName(cargoName);
    }

    public void setCargoName(Cargo cargo) {
        this.cargoName = cargo.getName();
    }

    public Status getStatusName() {
        return Status.valueOfName(statusName);
    }

    public void setStatusName(Status statusName) {
        this.statusName = statusName.getName();
    }

    @Override
    public String toString() {
        return "MembroDTO[" +
                "id=" + id +
                ", CPF='" + CPF + '\'' +
                ", dataNascimento'" + dataNascimento + '\'' +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargoName + '\'' +
                ", status='" + statusName + '\'' +
                ']';
    }
}
