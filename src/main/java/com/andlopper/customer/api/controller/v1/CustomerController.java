package com.andlopper.customer.api.controller.v1;

import com.andlopper.customer.api.controller.v1.request.CustomerRequest;
import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.entity.CustomerEntity;
import com.andlopper.customer.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Endpoint para criar ou atualizar um cliente
    @PostMapping
    public CustomerResponse saveOrUpdateCustomer(@RequestBody CustomerRequest customerEntity) {
        return customerService.saveCustomer(customerEntity);
    }

    // Endpoint para buscar um cliente pelo ID
    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerEmtities = customerService.getAllCustomers();
        return new ResponseEntity<>(customerEmtities, HttpStatus.OK);
    }

    // Endpoint para atualizar um cliente existente pelo ID
    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id,
                                           @Valid @RequestBody CustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }

    // Endpoint para excluir um cliente pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método PATCH para atualizar parcialmente um cliente pelo ID
    @PatchMapping("/{id}")
    public CustomerResponse partialUpdateCustomer(@PathVariable Long id,
                                                  @Valid @RequestBody CustomerRequest request) {
        return customerService.partialUpdateCustomer(id, request);
    }
}
