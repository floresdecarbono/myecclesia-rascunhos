package com.bookstore.meccrascunhos.controllers.docs;

import com.bookstore.meccrascunhos.models.dtos.LocalDTO;
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

public interface LocalControllerDocs {

    @Operation(
            summary = "Encontra todos os Locais",
            description = "Busca todos os Locais salvos no Banco de Dados",
            tags = {"Locais"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = LocalDTO.class))
                                    )}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping
    ResponseEntity<List<LocalDTO>> findAll();


    @Operation(
            summary = "Busca um Local",
            description = "Busca um Local no Banco de Dados através do seu ID",
            tags = {"Locais"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping(value = "/{id}")
    ResponseEntity<LocalDTO> findById(@PathVariable UUID id);


    @Operation(
            summary = "Salvar Local",
            description = "Salva um Local no Banco de Dados",
            tags = {"Locais"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping
    ResponseEntity<LocalDTO> insert(@RequestBody LocalDTO requestDTO);


    @Operation(
            summary = "Atualizar Local",
            description = "Atualiza o dado de um determinado Local, tendo como parâmetro o seu ID",
            tags = {"Locais"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PutMapping(value = "/{id}")
    ResponseEntity<LocalDTO> update(@PathVariable UUID id, @RequestBody LocalDTO requestDTO);


    @Operation(
            summary = "Deletar Local",
            description = "Deleta um Local do Banco de Dados",
            tags = {"Locais"},
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
