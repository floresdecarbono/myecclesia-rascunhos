package com.bookstore.meccrascunhos.services;

import com.bookstore.meccrascunhos.exceptions.RequiredObjectIsNullException;
import com.bookstore.meccrascunhos.exceptions.ResourceNotFoundException;
import com.bookstore.meccrascunhos.models.Local;
import com.bookstore.meccrascunhos.models.dtos.LocalDTO;
import com.bookstore.meccrascunhos.models.enums.Estado;
import com.bookstore.meccrascunhos.models.mappers.LocalMapper;
import com.bookstore.meccrascunhos.repositories.LocalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalService {

    private Logger logger = LoggerFactory.getLogger(LocalService.class.getName());
    private final LocalRepository repository;

    public LocalService(LocalRepository repository) {
        this.repository = repository;
    }

    public List<LocalDTO> findAll() {
        return repository.findAll().stream()
                .map(LocalMapper.INSTANCE::toDTO)
                .toList();
    }

    public LocalDTO findById(UUID id) {
        Local entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local de ID " + id + " não encontrado."));

        return LocalMapper.INSTANCE.toDTO(repository.save(entity));
    }

    public LocalDTO insert(LocalDTO requestDTO) {

        if (requestDTO == null) throw new RequiredObjectIsNullException();

        logger.info("CONVERTENDO O DTO LOCAL EM ENTIDADE");
        Local entity = LocalMapper.INSTANCE.toEntity(requestDTO);

        logger.info("SALVANDO ENTIDADE LOCAL NO REPOSITORY");
        logger.info("CONVERTENDO A ENTIDADE LOCAL EM DTO");

        LocalDTO responseDTO = LocalMapper.INSTANCE.toDTO(repository.save(entity));
        return responseDTO;
    }

    public LocalDTO update(UUID id, LocalDTO requestDTO) {

        if (id == null || requestDTO == null) throw new RequiredObjectIsNullException();

        logger.info("BUSCANDO LOCAL DE ID" + id + ".");

        Local entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local de ID " + id + " não encontrado."));
        updateData(entity, requestDTO);

        LocalDTO responseDTO = LocalMapper.INSTANCE.toDTO(repository.save(entity));

        return responseDTO;
    }

    public void delete(UUID id) {
        Local entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local de ID " + id +  "não encontrado."));

        repository.delete(entity);
    }

    private void updateData(Local oldData, LocalDTO newData) {
        oldData.setDescricao(newData.getDescricao());
        oldData.setLogradouro(newData.getLogradouro());
        oldData.setNumero(newData.getNumero());
        oldData.setBairro(newData.getBairro());
        oldData.setCidade(newData.getCidade());
        oldData.setEstado(Estado.valueOf(newData.getEstado()));
    }


}
