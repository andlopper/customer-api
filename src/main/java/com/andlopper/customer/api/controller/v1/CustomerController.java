package com.andlopper.customer.api.controller.v1;

import com.andlopper.customer.api.controller.v1.request.CustomerRequest;
import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "customer-api")
@RequestMapping("/customers")
public class CustomerController implements CustomerAPI{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse saveCustomer(@RequestBody CustomerRequest customerEntity) {
        return customerService.saveCustomer(customerEntity);
    }

    @Operation(summary = "Busca um cliente pelo ID", description = "Retorna um cliente por seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id) {
        return customerService.getCustomerById(id);
    }

    @Operation(summary = "Busca todos os clientes", description = "Retorna todos clientes encontrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))})
    })
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerEmtities = customerService.getAllCustomers();
        return new ResponseEntity<>(customerEmtities, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um cliente", description = "Retorna o cliente recém atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id,
                                           @Valid @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @Operation(summary = "Remove um cliente", description = "Remove o cliente com ID especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem conteúdo", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Atualiza parcialmente um cliente", description = "Retorna o cliente atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado - O cliente não foi encontrado", content = @Content)
    })
    @PatchMapping("/{id}")
    public CustomerResponse partialUpdateCustomer(@PathVariable("id") @Parameter(name = "id", description = "ID do cliente", example = "1") Long id,
                                                  @Valid @RequestBody CustomerRequest request) {
        return customerService.partialUpdateCustomer(id, request);
    }
}
