package com.bookstore.meccrascunhos.repositories;

import com.bookstore.meccrascunhos.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
