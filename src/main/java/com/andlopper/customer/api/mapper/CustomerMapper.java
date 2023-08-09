package com.andlopper.customer.api.mapper;

import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerResponse fromEntityToResponse(CustomerEntity from) {
        var toResponse = new CustomerResponse();
        toResponse.setEmail(from.getEmail());
        toResponse.setName(from.getName());
        toResponse.setPhone(from.getPhone());
        return toResponse;
    }

}
