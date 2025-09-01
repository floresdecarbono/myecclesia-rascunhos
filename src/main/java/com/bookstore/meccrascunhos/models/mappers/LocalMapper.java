package com.bookstore.meccrascunhos.models.mappers;

import com.bookstore.meccrascunhos.models.Local;
import com.bookstore.meccrascunhos.models.dtos.LocalDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocalMapper {

    LocalMapper INSTANCE = Mappers.getMapper(LocalMapper.class);

    @Mappings({
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "numero", target = "numero"),
            @Mapping(source = "logradouro", target = "logradouro"),
            @Mapping(source = "bairro", target = "bairro"),
            @Mapping(source = "cidade", target = "cidade"),
            @Mapping(source = "estado", target = "estado")
    })
    Local toEntity(LocalDTO dto);

    @Mappings({
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "numero", target = "numero"),
            @Mapping(source = "logradouro", target = "logradouro"),
            @Mapping(source = "bairro", target = "bairro"),
            @Mapping(source = "cidade", target = "cidade"),
            @Mapping(source = "estado", target = "estado")
    })
    LocalDTO toDTO(Local entity);


}
