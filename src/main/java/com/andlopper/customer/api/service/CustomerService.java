package com.andlopper.customer.api.service;

import com.andlopper.customer.api.controller.v1.request.CustomerRequest;
import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.exception.CustomerNotFoundException;
import com.andlopper.customer.api.mapper.CustomerMapper;
import com.andlopper.customer.api.repository.CustomerRepository;
import com.andlopper.customer.api.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired //TODO Remover isso aqui e parar de dar a bunda
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //TODO Adicionar documentação ao invés de comentários
    //Método para criar ou atualizar um cliente
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    // Método para buscar um cliente pelo ID
    public Optional<CustomerEntity> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Método para listar todos os clientes
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Método para atualizar um cliente existente pelo ID
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        //TODO Adicionar logs "log.info("vai fazer tal coisa");
        var actualCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado")); //TODO parametrizar mensagens de erro atraves do arquivo message.properties

        actualCustomer.setName(request.getName());
        actualCustomer.setEmail(request.getEmail());
        actualCustomer.setPhone(request.getPhone());

        customerRepository.save(actualCustomer);

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    // Método para excluir um cliente pelo ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}