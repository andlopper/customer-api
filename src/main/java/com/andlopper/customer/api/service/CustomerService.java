package com.andlopper.customer.api.service;

import com.andlopper.customer.api.controller.v1.request.CustomerRequest;
import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.entity.CustomerEntity;
import com.andlopper.customer.api.exception.CustomerNotFoundException;
import com.andlopper.customer.api.mapper.CustomerMapper;
import com.andlopper.customer.api.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final MessageSource messageSource;

    public CustomerService(CustomerRepository customerRepository, MessageSource messageSource) {
        this.customerRepository = customerRepository;
        this.messageSource = messageSource;
    }

    /**
     * Cria um novo cliente.
     * @param request Objeto contendo os dados do cliente a ser criado.
     * @return O cliente criado.
     */
    public CustomerResponse saveCustomer(CustomerRequest request) {
        log.info("[saveCustomer] Criando novo cliente");

        var actualCustomer = new CustomerEntity();

        actualCustomer.setName(request.getName());
        actualCustomer.setEmail(request.getEmail());
        actualCustomer.setPhone(request.getPhone());

        customerRepository.save(actualCustomer);

        log.info("[saveCustomer] Cliente criado com id: {}", actualCustomer.getId());

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    /**
     * Busca um cliente pelo ID.
     * @param id O ID do cliente a ser buscado.
     * @return O cliente encontrado ou null se nenhum cliente com o ID especificado for encontrado.
     */
    public CustomerResponse getCustomerById(Long id) {
        log.info("[getCustomerById] Buscando cliente com id: {}", id);

        var actualCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(messageSource.getMessage("customer.not.found", new Object[]{id}, null)));

        log.info("[getCustomerById] Cliente {} encontrado", id);

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    /**
     * Busca todos os clientes.
     * @return Todos os clientes encontrados.
     */
    public List<CustomerResponse> getAllCustomers() {
        log.info("[getAllCustomers] Listando todos os clientes");

        var customers = customerRepository.findAll();

        log.info("[getAllCustomers] Todos clientes listados");

        return CustomerMapper.fromEntityToResponse(customers);
    }

    /**
     * Atualiza um cliente.
     * @param id O ID do cliente a ser atualizado.
     * @param request Objeto contendo todos os dados do cliente a ser atualizado.
     * @return O cliente atualizado.
     */
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {

        log.info("[updateCustomer] Atualizando cliente com id: {}", id);
        var actualCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(messageSource.getMessage("customer.not.found", new Object[]{id}, null)));

        actualCustomer.setName(request.getName());
        actualCustomer.setEmail(request.getEmail());
        actualCustomer.setPhone(request.getPhone());

        customerRepository.save(actualCustomer);

        log.info("[updateCustomer] Cliente {} atualizado", id);

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    /**
     * Atualiza parcialmente um cliente.
     * @param id O ID do cliente a ser atualizado parcialmente.
     * @param request O objeto contendo os dados a serem atualizados.
     * @return Os dados completos do cliente atualizado.
     */
    public CustomerResponse partialUpdateCustomer(Long id, CustomerRequest request) {
        log.info("[partialUpdateCustomer] Atualizando parcialmente cliente: {}", id);

        var actualCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(messageSource.getMessage("customer.not.found", new Object[]{id}, null)));

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

        log.info("[partialUpdateCustomer] Cliente {} parcialmente atualizado", id);

        return CustomerMapper.fromEntityToResponse(actualCustomer);
    }

    /**
     * Remove um cliente
     * @param id O ID do cliente a ser removido.
     */
    public void deleteCustomer(Long id) {
        log.info("[deleteCustomer] Deletando cliente: {}", id);

        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(messageSource.getMessage("customer.not.found", new Object[]{id}, null)));

        customerRepository.deleteById(id);

        log.info("[deleteCustomer] Cliente {} deletado", id);
    }
}