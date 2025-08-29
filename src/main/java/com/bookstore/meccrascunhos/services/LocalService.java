package com.bookstore.meccrascunhos.services;

import com.bookstore.meccrascunhos.exceptions.ResourceNotFoundException;
import com.bookstore.meccrascunhos.models.Local;
import com.bookstore.meccrascunhos.repositories.LocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalService {

    private final LocalRepository repository;

    public LocalService(LocalRepository repository) {
        this.repository = repository;
    }

    public List<Local> findAll() {
        return repository.findAll();
    }

    public Local findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local de ID " + id + " não encontrado."));
    }

    public Local insert(Local local) {
        return repository.save(local);
    }

    public Local update(UUID id, Local novosDados) {
        Local local = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local de ID " + id + " não encontrado."));
        updateData(local, novosDados);

        return repository.save(local);
    }

    public void delete(UUID id) {
        Local local = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local de ID " + id +  "não encontrado."));

        repository.delete(local);
    }

    private void updateData(Local local, Local novosDados) {
        local.setDescricao(novosDados.getDescricao());
        local.setLogradouro(novosDados.getLogradouro());
        local.setNumero(novosDados.getNumero());
        local.setBairro(novosDados.getBairro());
        local.setCidade(novosDados.getCidade());
        local.setEstado(novosDados.getEstado());
    }


}
