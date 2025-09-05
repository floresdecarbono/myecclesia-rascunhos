package com.bookstore.meccrascunhos.controllers.docs;

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

public interface MembroControllerDocs {

    @Operation(
            summary = "Encontra todos os Membros",
            description = "Busca todos os Membros salvos no Banco de Dados",
            tags = {"Membros"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = MembroDTO.class))
                                    )}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping
    ResponseEntity<List<MembroDTO>> findAll();

    @Operation(
            summary = "Filtra Ativos",
            description = "Filtra os Membros do Banco de Dados com status 'Ativo'",
            tags = {"Membros"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = MembroDTO.class))
                                    )}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping("/ativos")
    ResponseEntity<List<MembroDTO>> findByAtivos();

    @Operation(
            summary = "Busca um Membro",
            description = "Busca um Membro no Banco de Dados através do seu ID",
            tags = {"Membros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping(value = "/{id}")
    ResponseEntity<MembroDTO> findById(@PathVariable UUID id);

    @Operation(
            summary = "Salvar Membro",
            description = "Salva um Membro no Banco de Dados",
            tags = {"Membros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<MembroDTO> insert(@RequestBody MembroDTO membro);

    @Operation(
            summary = "Atualizar Membro",
            description = "Atualiza o dado de um determinado Membro, tendo como parâmetro o seu ID",
            tags = {"Membros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PutMapping(value = "/{id}")
    ResponseEntity<MembroDTO> update(@PathVariable UUID id, @RequestBody MembroDTO updated);


    @Operation(
            summary = "Deletar Membro",
            description = "Deleta um membro do Banco de Dados",
            tags = {"Membros"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @DeleteMapping(value = "{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
