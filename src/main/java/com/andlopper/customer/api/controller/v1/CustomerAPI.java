package com.andlopper.customer.api.controller.v1;

import com.andlopper.customer.api.controller.v1.request.CustomerRequest;
import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface CustomerAPI {

    @Operation(summary = "Cria um novo cliente!", description = "Retorna o cliente recém criado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))})
    })
    CustomerResponse saveCustomer(@RequestBody CustomerRequest customerEntity);

}
