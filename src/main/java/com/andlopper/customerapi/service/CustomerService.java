package com.andlopper.customerapi.service;

import com.andlopper.customerapi.model.Customer;
import com.andlopper.customerapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Método para criar ou atualizar um cliente
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Método para buscar um cliente pelo ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Método para listar todos os clientes
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Método para atualizar um cliente existente pelo ID
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        } else {
            return null; // ou lançar uma exceção, se preferir
        }
    }

    // Método para excluir um cliente pelo ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}