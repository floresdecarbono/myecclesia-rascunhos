package com.bookstore.meccrascunhos.controllers;

import com.bookstore.meccrascunhos.controllers.docs.LocalControllerDocs;
import com.bookstore.meccrascunhos.models.Local;
import com.bookstore.meccrascunhos.models.dtos.LocalDTO;
import com.bookstore.meccrascunhos.services.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/locais")
public class LocalController implements LocalControllerDocs {

    private final LocalService service;

    public LocalController(LocalService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<LocalDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<LocalDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @Override
    public ResponseEntity<LocalDTO> insert(@RequestBody LocalDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.insert(requestDTO));
    }

    @PutMapping(value = "/{id}")
    @Override
    public ResponseEntity<LocalDTO> update(@PathVariable UUID id, @RequestBody LocalDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, requestDTO));
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
