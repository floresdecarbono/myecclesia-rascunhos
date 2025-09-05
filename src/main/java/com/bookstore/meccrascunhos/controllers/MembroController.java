package com.bookstore.meccrascunhos.controllers;

import com.bookstore.meccrascunhos.controllers.docs.MembroControllerDocs;
import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import com.bookstore.meccrascunhos.services.MembroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/membros")
@CrossOrigin(origins = "*")
@Tag(name = "Membros", description = "Endpoints que manipula e persiste os membros no Banco de Dados")
public class MembroController implements MembroControllerDocs {

    private final MembroService service;

    public MembroController(MembroService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<MembroDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/ativos")
    @Override
    public ResponseEntity<List<MembroDTO>> findByAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByAtivos());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<MembroDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @Override
    public ResponseEntity<MembroDTO> insert(@RequestBody MembroDTO membro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(membro));
    }

    @PutMapping(value = "/{id}")
    @Override
    public ResponseEntity<MembroDTO> update(@PathVariable UUID id, @RequestBody MembroDTO updated) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, updated));
    }

    @DeleteMapping(value = "{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
