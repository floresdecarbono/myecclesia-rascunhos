package com.bookstore.meccrascunhos.repositories;

import com.bookstore.meccrascunhos.models.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocalRepository extends JpaRepository<Local, UUID> {
}
