package com.andlopper.customer.api.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class CustomerRequest {

    @Schema(name = "Nome do cliente", example = "João da Silva")
    @NotBlank()
    private String name;

    @Schema(name = "Telefone do cliente", example = "(55) 98765-4321")
    @NotBlank()
    private String phone;

    @Schema(name = "E-mail do cliente", example = "nome@email.com")
    @NotBlank()
    private String email;

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
