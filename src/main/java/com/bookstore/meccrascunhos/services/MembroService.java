package com.bookstore.meccrascunhos.services;

import com.bookstore.meccrascunhos.controllers.MembroController;
import com.bookstore.meccrascunhos.exceptions.RequiredObjectIsNullException;
import com.bookstore.meccrascunhos.exceptions.ResourceNotFoundException;
import com.bookstore.meccrascunhos.models.Membro;
import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import com.bookstore.meccrascunhos.models.enums.Status;
import com.bookstore.meccrascunhos.models.mappers.MembroMapper;
import com.bookstore.meccrascunhos.repositories.MembroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.UUID;

@Service
public class MembroService {

    private Logger logger = LoggerFactory.getLogger(MembroService.class.getName());
    private final MembroRepository repository;

    public MembroService(MembroRepository repository) {
        this.repository = repository;
    }

    public List<MembroDTO> findAll() {
        logger.info("BUSCANDO TODOS OS MEMBROS.");

        List<MembroDTO> list = repository.findAll().stream()
                .map(MembroMapper.INSTANCE::toDTO).toList();
        list.forEach(MembroService::addLinks);

        return list;
    }

    public List<MembroDTO> findByAtivos() {
        logger.info("BUSCANDO TODOS OS MEMBROS ATIVOS.");

        List<MembroDTO> list = repository.findAll().stream()
                .filter(m -> m.getStatusCode() == Status.ATIVO)
                .map(MembroMapper.INSTANCE::toDTO).toList();
        list.forEach(MembroService::addLinks);

        return list;
    }

    public MembroDTO findById(UUID id) {
        logger.info("BUSCANDO MEMBRO DE ID " + id + ".");
        Membro entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membro de ID " + id + " não encontrado."));

        MembroDTO responseDTO = MembroMapper.INSTANCE.toDTO(entity);
        addLinks(responseDTO);

        return responseDTO;
    }

    public MembroDTO insert(MembroDTO membroDTO) {

        if (membroDTO == null) throw new RequiredObjectIsNullException();

        Membro entity = MembroMapper.INSTANCE.toEntity(membroDTO);
        logger.info("SALVANDO MEMBRO NO REPOSITORY.");

        MembroDTO responseDTO = MembroMapper.INSTANCE.toDTO(repository.save(entity));
        addLinks(responseDTO);

        return responseDTO;
    }

    public MembroDTO update(UUID id, MembroDTO novosDados) {

        if (id == null || novosDados == null) throw new RequiredObjectIsNullException();

        logger.info("BUSCANDO MEMBRO DE ID " + id + ".");
        Membro entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Membro de ID " + id + " não encontrado."));

        logger.info("COPIANDO AS PROPRIEDADES DO MEMBRO.");
        updateData(entity, novosDados);

        logger.info("SALVANDO AS NOVAS PROPRIEDADES DO MEMBRO.");
        MembroDTO responseDTO = MembroMapper.INSTANCE.toDTO(repository.save(entity));
        addLinks(responseDTO);

        return responseDTO;
    }

    public void delete(UUID id) {
        logger.info("BUSCANDO MEBRO DE ID " + id + ".");
        Membro entity = repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Membro de ID " + id + " não encontrado."));

        logger.info("DELETANDO MEMBRO DE ID " + id + ".");
        repository.delete(entity);
    }

    private void updateData(Membro membro, MembroDTO novosDados) {
        membro.setCPF(novosDados.getCPF());
        membro.setNome(novosDados.getNome());
        membro.setTelefone(novosDados.getTelefone());
        membro.setDataNascimento(novosDados.getDataNascimento());
        membro.setCargoCode(novosDados.getCargoName());
        membro.setStatusCode(novosDados.getStatusName());
    }

    private static void addLinks(MembroDTO dto) {
        dto.add(linkTo(methodOn(MembroController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(MembroController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(MembroController.class).insert(dto)).withRel("insert").withType("POST"));
        dto.add(linkTo(methodOn(MembroController.class).update(dto.getId(), dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(MembroController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
