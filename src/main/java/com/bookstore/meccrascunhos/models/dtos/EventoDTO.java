package com.bookstore.meccrascunhos.models.dtos;

import com.bookstore.meccrascunhos.models.Local;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class EventoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String descricao;
    private UUID localId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horario;

    public EventoDTO() {}

    public EventoDTO(UUID id, String descricao, UUID localId, LocalDate data, LocalTime horario) {
        this.id = id;
        this.descricao = descricao;
        this.localId = localId;
        this.data = data;
        this.horario = horario;
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

    public UUID getLocalId() {
        return localId;
    }

    public void setLocalId(UUID localId) {
        this.localId = localId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventoDTO eventoDTO = (EventoDTO) o;
        return Objects.equals(id, eventoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EventoDTO[" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", localId=" + localId +
                ", data=" + data +
                ", horario=" + horario +
                ']';
    }
}
