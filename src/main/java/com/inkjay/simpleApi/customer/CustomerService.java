package com.inkjay.simpleApi.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //get all customers
    public List<Customer> getCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }
    //get one customer by id
    public Optional<Customer> getCustomerById(UUID id){
    boolean exists = customerRepository.existsById(id);

        if (!exists) {
        throw new IllegalStateException("Customer with id " + id + " does not exist");
    }
        return customerRepository.findById(id);
    }

    // add new customer
    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if(customerOptional.isPresent()){
            throw new IllegalStateException("email in use");
        }
        customerRepository.save(customer);
    }

    //delete customer by id
    public void deleteCustomer(UUID id) {
        boolean exists = customerRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Customer with id " + id + " does not exist");
        }

        customerRepository.deleteById(id);
    }

    //update or change customer details
    @Transactional
    public void updateCustomer(Long id, String firstName, String lastName, String email, String phoneNumber) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Customer with id " + id + " does not exist"));

        if(firstName != null && firstName.length() > 0 && !Objects.equals(customer.getFirstName(),firstName)){
            customer.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(customer.getLastName(),lastName)){
            customer.setLastName(lastName);
        }

        if(email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)){
            customer.setEmail(email);
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

            if(customerOptional.isPresent()){
                throw new IllegalStateException("email in use");
            }

            if(phoneNumber != null && phoneNumber.length() >0 && !Objects.equals(customer.getPhoneNumber(),phoneNumber)){
             customer.setPhoneNumber(phoneNumber);

        }
    }
    }

}
