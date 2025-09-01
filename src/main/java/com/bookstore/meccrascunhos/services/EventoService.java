package com.bookstore.meccrascunhos.services;

import com.bookstore.meccrascunhos.exceptions.RequiredObjectIsNullException;
import com.bookstore.meccrascunhos.exceptions.ResourceNotFoundException;
import com.bookstore.meccrascunhos.models.Evento;
import com.bookstore.meccrascunhos.models.dtos.EventoDTO;
import com.bookstore.meccrascunhos.models.mappers.EventoMapper;
import com.bookstore.meccrascunhos.repositories.EventoRepository;
import com.bookstore.meccrascunhos.repositories.LocalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    private Logger logger = LoggerFactory.getLogger(EventoService.class.getName());
    private final EventoRepository eventoRepository;
    private final LocalRepository localRepository;

    public EventoService(EventoRepository eventoRepository, LocalRepository localRepository) {
        this.eventoRepository = eventoRepository;
        this.localRepository = localRepository;
    }

    public List<EventoDTO> findAll() {
        logger.info("BUSCANDO LISTA DE EVENTOS.");

        return eventoRepository.findAll().stream()
                .map(EventoMapper.INSTANCE::toDTO)
                .toList();
    }

    public EventoDTO findById(UUID id) {
        logger.info("BUSCANDO EVENTO.");

        Evento entity =  eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento de ID " + id + " não encontrado."));

        return EventoMapper.INSTANCE.toDTO(entity);
    }

    public EventoDTO insert(EventoDTO dto) {

        if (dto == null) throw new RequiredObjectIsNullException();

        Evento entity = EventoMapper.INSTANCE.toEntity(dto);
        logger.info("SALVANDO EVENTO NO REPOSITORY.");

        EventoDTO responseDTO = EventoMapper.INSTANCE.toDTO(eventoRepository.save(entity));
        return responseDTO;
    }

    public EventoDTO update(UUID id, EventoDTO requestDTO) {
        logger.info("BUSCANDO EVENTO DE ID " + id + ".");
        Evento entity = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento de ID " + id + " não encontrado."));

        logger.info("SALVANDO AS NOVAS PROPRIEDADES DO EVENTO.");
        updateData(entity, requestDTO);

        return EventoMapper.INSTANCE.toDTO(eventoRepository.save(entity));

    }

    public void delete(UUID id) {
        logger.info("BUSCANDO EVENTO DE ID " + id + ".");
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento de ID " + id + " não encontrado."));

        logger.info("DELETANDO EVENTO.");
        eventoRepository.delete(evento);
    }

    public void updateData(Evento oldData, EventoDTO newData) {
        oldData.setDescricao(newData.getDescricao());
        oldData.setLocal(localRepository.findById(newData.getLocalId())
                .orElseThrow(ResourceNotFoundException::new));
        oldData.setData(newData.getData());
        oldData.setHorario(newData.getHorario());
    }

}
