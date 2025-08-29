package com.bookstore.meccrascunhos.models.mappers;

import com.bookstore.meccrascunhos.models.Membro;
import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

public interface MembroMapper {

    MembroMapper INSTANCE = Mappers.getMapper(MembroMapper.class);

    @Mappings({
            @Mapping(source = "cargoName", target = "cargoCode"),
            @Mapping(source = "statusName", target = "statusCode"),
            @Mapping(source = "dataNascimento", target = "dataNascimento")
    })
    Membro membroDTOtoMembro(MembroDTO dto);

    @Mappings({
            @Mapping(source = "cargoCode", target = "cargoName"),
            @Mapping(source = "statusCode", target = "statusName"),
            @Mapping(source = "dataNascimento", target = "dataNascimento")
    })
    MembroDTO membroToMembroDTO(Membro entity);

}
