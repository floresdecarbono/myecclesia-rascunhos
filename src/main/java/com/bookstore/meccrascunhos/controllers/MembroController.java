package com.bookstore.meccrascunhos.controllers;

import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import com.bookstore.meccrascunhos.services.MembroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/membros")
@CrossOrigin(origins = "*")
public class MembroController {

    private final MembroService service;

    public MembroController(MembroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MembroDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<MembroDTO>> findByAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByAtivos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MembroDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MembroDTO> insert(@RequestBody MembroDTO membro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(membro));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MembroDTO> update(@PathVariable UUID id, @RequestBody MembroDTO updated) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, updated));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
