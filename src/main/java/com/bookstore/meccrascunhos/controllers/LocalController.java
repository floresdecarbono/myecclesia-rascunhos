package com.bookstore.meccrascunhos.controllers;

import com.bookstore.meccrascunhos.models.Local;
import com.bookstore.meccrascunhos.services.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/locais")
public class LocalController {

    private final LocalService service;

    public LocalController(LocalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Local>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Local> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Local> insert(@RequestBody Local local) {
        return ResponseEntity.status(HttpStatus.OK).body(service.insert(local));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Local> update(@PathVariable UUID id, @RequestBody Local local) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, local));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
