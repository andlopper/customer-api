package com.andlopper.customer.api.controller.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class CustomerResponse {

    @Schema(name = "id", example = "1")
    private Long id;

    @Schema(name = "name", example = "João da Silva")
    private String name;

    @Schema(name = "phone", example = "(55) 98765-4321")
    private String phone;

    @Schema(name = "email", example = "nome@email.com")
    private String email;

    public CustomerResponse() {
    }

    public CustomerResponse(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
