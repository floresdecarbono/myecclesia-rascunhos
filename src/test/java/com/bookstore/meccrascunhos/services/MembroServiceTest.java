package com.bookstore.meccrascunhos.services;

import com.bookstore.meccrascunhos.models.Membro;
import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import com.bookstore.meccrascunhos.models.enums.Cargo;
import com.bookstore.meccrascunhos.models.enums.Status;
import com.bookstore.meccrascunhos.repositories.MembroRepository;
import com.bookstore.meccrascunhos.unitetests.mappers.mocks.MockMembro;
import com.jayway.jsonpath.spi.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MembroServiceTest {

    MockMembro input;

    @InjectMocks
    private MembroService service;

    @Mock
    MembroRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockMembro();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        Membro entity = input.mockEntity(7);
        UUID entityId = UUID.randomUUID();

        entity.setId(entityId);
        repository.save(entity);

        when(repository.findById(entityId)).thenReturn(Optional.of(entity));

        var result = service.findById(entityId);

        assertNotNull(result);
        assertNotNull(result.getId());

        assertEquals("Entidade 7", result.getNome());
        assertEquals("777.777.777-77", result.getCPF());
        assertEquals(LocalDate.of(2001, 01, 01), result.getDataNascimento());
        assertEquals("(77) 7777-7777", result.getTelefone());
        assertEquals(Cargo.LIDER, result.getCargoName());
        assertEquals(Status.ATIVO, result.getStatusName());

        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("self")
                && l.getType().equals("GET")
                && l.getHref().endsWith("membros/" + entityId)));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("findAll")
                        && l.getType().equals("GET")
                        && l.getHref().endsWith("membros")));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("insert")
                        && l.getType().equals("POST")
                        && l.getHref().endsWith("membros")));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("update")
                        && l.getType().equals("PUT")
                        && l.getHref().endsWith("membros/" + entityId)));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("delete")
                        && l.getType().equals("DELETE")
                        && l.getHref().endsWith("membros/" + entityId)));



    }

    @Test
    void insert() {
        Membro entity = input.mockEntity(4);
        UUID id = UUID.randomUUID();

        Membro persisted = entity;
        persisted.setId(id);

        MembroDTO dto = input.mockDTO(4);
        dto.setId(id);

        when(repository.save(any(Membro.class))).thenReturn(persisted);
        MembroDTO result = service.insert(dto);

        assertNotNull(result);
        assertNotNull(result.getId());

        assertEquals("Entidade 4", result.getNome());
        assertEquals("444.444.444-44", result.getCPF());
        assertEquals("(44) 4444-4444", result.getTelefone());
        assertEquals(Cargo.PASTOR, result.getCargoName());
        assertEquals(Status.ATIVO, result.getStatusName());

        assertEquals(LocalDate.of(2001, 01, 01), result.getDataNascimento());


        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("self")
                        && l.getType().equals("GET")
                        && l.getHref().endsWith("membros/" + id)));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("findAll")
                        && l.getType().equals("GET")
                        && l.getHref().endsWith("membros")));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("insert")
                        && l.getType().equals("POST")
                        && l.getHref().endsWith("membros")));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("update")
                        && l.getType().equals("PUT")
                        && l.getHref().endsWith("membros/" + id)));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("delete")
                        && l.getType().equals("DELETE")
                        && l.getHref().endsWith("membros/" + id)));

    }

    @Test
    void update() {
        Membro membro = input.mockEntity(9);
        Membro persisted = membro;

        UUID id = UUID.randomUUID();
        persisted.setId(id);

        MembroDTO dto = input.mockDTO(9);

        when(repository.findById(id)).thenReturn(Optional.of(membro));
        when(repository.save(any(Membro.class))).thenReturn(persisted);

        MembroDTO result = service.update(id, dto);

        assertNotNull(result);
        assertNotNull(result.getId());

        assertEquals("Entidade 9", result.getNome());
        assertEquals("999.999.999-99", result.getCPF());
        assertEquals("(99) 9999-9999", result.getTelefone());
        assertEquals(Cargo.LIDER, result.getCargoName());

        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("self")
                        && l.getType().equals("GET")
                        && l.getHref().endsWith("membros/" + id)));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("findAll")
                        && l.getType().equals("GET")
                        && l.getHref().endsWith("membros")));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("insert")
                        && l.getType().equals("POST")
                        && l.getHref().endsWith("membros")));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("update")
                        && l.getType().equals("PUT")
                        && l.getHref().endsWith("membros/" + id)));
        assertNotNull(result.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("delete")
                        && l.getType().equals("DELETE")
                        && l.getHref().endsWith("membros/" + id)));

    }

    @Test
    void delete() {
        Membro entity = input.mockEntity(2);
        UUID id = UUID.randomUUID();
        entity.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        service.delete(id);
        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, times(1)).delete(any(Membro.class));
        verifyNoMoreInteractions(repository);
    }

}