package com.inkjay.simpleApi.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }
    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if(customerOptional.isPresent()){
            throw new IllegalStateException("email in use");
        }
        customerRepository.save(customer);
    }
    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Customer with id " + id + " does not exist");
        }

        customerRepository.deleteById(id);
    }
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
