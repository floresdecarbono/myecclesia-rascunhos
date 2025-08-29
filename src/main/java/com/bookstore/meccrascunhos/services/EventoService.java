package com.bookstore.meccrascunhos.services;

import com.bookstore.meccrascunhos.exceptions.ResourceNotFoundException;
import com.bookstore.meccrascunhos.models.Evento;
import com.bookstore.meccrascunhos.repositories.EventoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    private Logger logger = LoggerFactory.getLogger(EventoService.class.getName());
    private EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public List<Evento> findAll() {
        logger.info("BUSCANDO LISTA DE EVENTOS.");

        return repository.findAll();
    }

    public Evento findById(UUID id) {
        logger.info("BUSCANDO EVENTO.");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento de ID " + id + " não encontrado."));
    }

    public Evento insert(Evento evento) {
        logger.info("SALVANDO EVENTO NO REPOSITORY.");

        return repository.save(evento);

    }

    public Evento update(UUID id, Evento novosDados) {
        logger.info("BUSCANDO EVENTO DE ID " + id + ".");
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento de ID " + id + " não encontrado."));

        logger.info("SALVANDO AS NOVAS PROPRIEDADES DO EVENTO.");
        updateData(novosDados, evento);

        return repository.save(evento);

    }

    public void delete(UUID id) {
        logger.info("BUSCANDO EVENTO DE ID " + id + ".");
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento de ID " + id + " não encontrado."));

        logger.info("DELETANDO EVENTO.");
        repository.delete(evento);
    }

    public void updateData(Evento oldData, Evento newData) {
        oldData.setDescricao(newData.getDescricao());
        oldData.setLocal(newData.getLocal());
        oldData.setData(newData.getData());
        oldData.setHora(newData.getHora());
    }

}
