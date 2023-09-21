package com.andlopper.customer.api.controller.v1;

import com.andlopper.customer.api.controller.v1.request.CustomerRequest;
import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Classe utilitária para centralizar as annotations do swagger
 */

public interface CustomerAPI {

    @Operation(summary = "Cria um novo cliente", description = "Retorna o cliente recém criado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))})
    })
    CustomerResponse saveCustomer(@RequestBody CustomerRequest customerEntity);

    @Operation(summary = "Busca um cliente pelo ID", description = "Retorna um cliente por seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    CustomerResponse getCustomerById(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id);

    @Operation(summary = "Busca todos os clientes", description = "Retorna todos clientes encontrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))})
    })
    ResponseEntity<List<CustomerResponse>> getAllCustomers();

    @Operation(summary = "Atualiza um cliente", description = "Retorna o cliente recém atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    CustomerResponse updateCustomer(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id,
                                           @Valid @org.springframework.web.bind.annotation.RequestBody CustomerRequest request);

    @Operation(summary = "Remove um cliente", description = "Remove o cliente com ID especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    ResponseEntity<Void> deleteCustomer(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id);

    @Operation(summary = "Atualiza parcialmente um cliente", description = "Retorna o cliente atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    CustomerResponse partialUpdateCustomer(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id,
                                                  @Valid @org.springframework.web.bind.annotation.RequestBody CustomerRequest request);
}
