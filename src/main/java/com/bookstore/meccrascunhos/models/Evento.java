package com.bookstore.meccrascunhos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_EVENTOS")
public class Evento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Local local;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;

    public Evento() {}

    public Evento(UUID id, String descricao, Local local, LocalDate data, LocalTime hora) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.hora = hora;
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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Evento[" +
                "id='" + id + '\'' +
                "descricao='" + descricao + '\'' +
                ", local='" + local + '\'' +
                ", data=" + data +
                ", hora=" + hora +
                ']';
    }
}
