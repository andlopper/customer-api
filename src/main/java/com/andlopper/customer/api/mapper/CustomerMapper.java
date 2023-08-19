package com.andlopper.customer.api.mapper;

import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.entity.CustomerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerResponse fromEntityToResponse(CustomerEntity from) {
        var toResponse = new CustomerResponse();
        toResponse.setEmail(from.getEmail());
        toResponse.setName(from.getName());
        toResponse.setPhone(from.getPhone());
        return toResponse;
    }

    public static List<CustomerResponse> fromEntityToResponse(List<CustomerEntity> customers) {

        return customers.stream()
                .map(CustomerMapper::fromEntityToResponse) // Mapear cada entidade para resposta
                .collect(Collectors.toList());
    }

}
