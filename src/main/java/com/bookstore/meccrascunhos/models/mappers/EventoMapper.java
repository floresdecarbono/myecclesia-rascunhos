package com.bookstore.meccrascunhos.models.mappers;

import com.bookstore.meccrascunhos.exceptions.RequiredObjectIsNullException;
import com.bookstore.meccrascunhos.models.Evento;
import com.bookstore.meccrascunhos.models.Local;
import com.bookstore.meccrascunhos.models.dtos.EventoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    @Mappings( {
            @Mapping(target = "local", source = "localId",  qualifiedByName = "mapUUIDtoLocal"),
            @Mapping(target = "data", source = "data"),
            @Mapping(target = "horario", source = "horario")
    })
    Evento toEntity(EventoDTO dto);

    @Mappings({
            @Mapping(target = "localId", source = "local", qualifiedByName = "mapLocalToUUID"),
            @Mapping(target = "data", source = "data"),
            @Mapping(target = "horario", source = "horario")
    })
    EventoDTO toDTO(Evento evento);

    @Named("mapUUIDtoLocal")
    default Local mapUUIDtoLocal(UUID localId) {
        if (localId == null) {
            throw new RequiredObjectIsNullException();
        }

        Local local = new Local();
        local.setId(localId);
        return local;
    }

    @Named("mapLocalToUUID")
    default UUID mapLocalToUUID(Local local) {
        if (local == null) {
            throw new RequiredObjectIsNullException();
        }

        return local.getId();
    }



}
