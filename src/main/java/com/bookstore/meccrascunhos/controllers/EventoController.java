package com.bookstore.meccrascunhos.controllers;

import com.bookstore.meccrascunhos.controllers.docs.EventoControllerDocs;
import com.bookstore.meccrascunhos.models.dtos.EventoDTO;
import com.bookstore.meccrascunhos.services.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/eventos")
public class EventoController implements EventoControllerDocs {

    private EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<EventoDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<EventoDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @Override
    public ResponseEntity<EventoDTO> insert(@RequestBody EventoDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(requestDTO));
    }

    @PutMapping(value = "/{id}")
    @Override
    public ResponseEntity<EventoDTO> update(@PathVariable UUID id, @RequestBody EventoDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, requestDTO));
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }



}
