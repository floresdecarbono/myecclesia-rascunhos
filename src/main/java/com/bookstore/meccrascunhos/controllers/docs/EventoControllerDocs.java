package com.bookstore.meccrascunhos.controllers.docs;

import com.bookstore.meccrascunhos.models.dtos.EventoDTO;
import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface EventoControllerDocs {

    @Operation(
            summary = "Encontra todos os Eventos",
            description = "Busca todos os Eventos salvos no Banco de Dados",
            tags = {"Eventos"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = EventoDTO.class))
                                    )}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping()
    ResponseEntity<List<EventoDTO>> findAll();

    @Operation(
            summary = "Busca um Evento",
            description = "Busca um Evento no Banco de Dados através do seu ID",
            tags = {"Eventos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping(value = "/{id}")
    ResponseEntity<EventoDTO> findById(@PathVariable UUID id);

    @Operation(
            summary = "Salvar Evento",
            description = "Salva um Membro no Banco de Dados",
            tags = {"Eventos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<EventoDTO> insert(@RequestBody EventoDTO requestDTO);


    @Operation(
            summary = "Atualizar Evento",
            description = "Atualiza o dado de um determinado Evento, tendo por parâmetro o seu ID",
            tags = {"Eventos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PutMapping(value = "/{id}")
    ResponseEntity<EventoDTO> update(@PathVariable UUID id, @RequestBody EventoDTO requestDTO);


    @Operation(
            summary = "Deletar Evento",
            description = "Deleta um membro do Banco de Dados",
            tags = {"Eventos"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
