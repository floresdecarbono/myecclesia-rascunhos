package com.bookstore.meccrascunhos.controllers;

import com.bookstore.meccrascunhos.models.Evento;
import com.bookstore.meccrascunhos.services.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/eventos")
public class EventoController {

    private EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping()
    ResponseEntity<List<Evento>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Evento> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    ResponseEntity<Evento> insert(@RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(evento));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Evento> update(@PathVariable UUID id, @RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, evento));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }



}
