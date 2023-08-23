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

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired //TODO Remover isso aqui
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //TODO Adicionar documentação ao invés de comentários
    /**
     * Método para criar um cliente
     * @param request Dados da requisição
     * @return Dados salvos
     */
    public CustomerResponse saveCustomer(CustomerRequest request) {
        log.info("Criando novo cliente");

        var actualCustomer = new CustomerEntity();

        actualCustomer.setName(request.getName());
        actualCustomer.setEmail(request.getEmail());
        actualCustomer.setPhone(request.getPhone());

        customerRepository.save(actualCustomer);

        log.info("Cliente criado com id: {}", actualCustomer.getId());

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    // Método para buscar um cliente pelo ID
    public CustomerResponse getCustomerById(Long id) {
        log.info("Buscando cliente com id: {}", id);

        var actualCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente nao encontrado"));

        log.info("Cliente encontrado");

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    // Método para listar todos os clientes
    public List<CustomerResponse> getAllCustomers() {
        log.info("Listando todos os clientes");

        var customers = customerRepository.findAll();

        log.info("Todos clientes listados");

        return CustomerMapper.fromEntityToResponse(customers);
    }

    // Método para atualizar um cliente existente pelo ID
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        //TODO Adicionar logs "log.info("vai fazer tal coisa");
        log.info("Atualizando cliente com id: {}", id);
        var actualCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado")); //TODO parametrizar mensagens de erro atraves do arquivo message.properties

        actualCustomer.setName(request.getName());
        actualCustomer.setEmail(request.getEmail());
        actualCustomer.setPhone(request.getPhone());

        customerRepository.save(actualCustomer);

        log.info("Cliente {} atualizado", id);

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    public CustomerResponse partialUpdateCustomer(Long id, CustomerRequest request) {
        log.info("Atualizando parcialmente cliente: {}", id);

        var actualCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado"));

        if (request.getName() != null) {
            actualCustomer.setName(request.getName());
        }

        if (request.getPhone() != null) {
            actualCustomer.setPhone(request.getPhone());
        }

        if (request.getEmail() != null) {
            actualCustomer.setEmail(request.getEmail());
        }

        customerRepository.save(actualCustomer);

        log.info("Cliente {} parcialmente atualizado", id);

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    // Método para excluir um cliente pelo ID
    public void deleteCustomer(Long id) {
        log.info("Deletando cliente: {}", id);

        customerRepository.deleteById(id);

        log.info("Cliente {} deletado", id);
    }
}