package com.bookstore.meccrascunhos.services;

import com.bookstore.meccrascunhos.exceptions.RequiredObjectIsNullException;
import com.bookstore.meccrascunhos.models.Membro;
import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import com.bookstore.meccrascunhos.models.enums.Cargo;
import com.bookstore.meccrascunhos.models.enums.Status;
import com.bookstore.meccrascunhos.repositories.MembroRepository;
import com.bookstore.meccrascunhos.unitetests.mappers.mocks.MockMembro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
        List<Membro> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        List<MembroDTO> membros = service.findAll();

        MembroDTO membroDois = membros.get(2);
        asserts(2, membroDois);

        MembroDTO membroSete = membros.get(7);
        asserts(7, membroSete);

        MembroDTO membroOnze = membros.get(11);
        asserts(11, membroOnze);

        MembroDTO membroCatorze = membros.get(14);
        asserts(14, membroCatorze);

    }

    @Test
    void findById() {
        Membro entity = input.mockEntity(7);
        UUID entityId = UUID.randomUUID();

        entity.setId(entityId);
        repository.save(entity);

        when(repository.findById(entityId)).thenReturn(Optional.of(entity));

        var result = service.findById(entityId);

        asserts(7, result);

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

        asserts(4, result);

    }

    @Test
    void testInsertWithNullMembro() {
        Exception err = assertThrows(RequiredObjectIsNullException.class,
                () -> service.insert(null));


        String expectedMessage = "Não é permitida a persistência de objetos nulos.";
        String actualMessage = err.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

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

        asserts(9, result);

    }

    @Test
    void testUpdateWithNullPerson() {
        Exception err = assertThrows(RequiredObjectIsNullException.class,
                () -> service.update(null, null));

        String expectedMessage = "Não é permitida a persistência de objetos nulos.";
        String actualMessage = err.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
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

    void asserts(int number, MembroDTO dto) {
        assertNotNull(dto);
        assertNotNull(dto.getId());

        assertEquals("Entidade " + number, dto.getNome());
        assertEquals(MockMembro.criaCPF(number), dto.getCPF());
        assertEquals(LocalDate.of(2001, 01, 01), dto.getDataNascimento());
        assertEquals(MockMembro.criaTelefone(number), dto.getTelefone());

        if (number % 2 == 0) {
            assertEquals(Cargo.PASTOR, dto.getCargoName());
            assertEquals(Status.ATIVO, dto.getStatusName());
        }
        else {
            assertEquals(Cargo.LIDER, dto.getCargoName());
            assertEquals(Status.INATIVO, dto.getStatusName());
        }


        assertNotNull(dto.getLinks());
        assertNotNull(dto.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("self")
                        && l.getType().equals("GET")
                        && l.getHref().endsWith("membros/" + dto.getId())));
        assertNotNull(dto.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("findAll")
                        && l.getType().equals("GET")
                        && l.getHref().endsWith("membros")));
        assertNotNull(dto.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("insert")
                        && l.getType().equals("POST")
                        && l.getHref().endsWith("membros")));
        assertNotNull(dto.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("update")
                        && l.getType().equals("PUT")
                        && l.getHref().endsWith("membros/" + dto.getId())));
        assertNotNull(dto.getLinks().stream()
                .anyMatch(l -> l.getRel().value().equals("delete")
                        && l.getType().equals("DELETE")
                        && l.getHref().endsWith("membros/" + dto.getId())));

    }

}