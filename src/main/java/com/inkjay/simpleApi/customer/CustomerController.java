package com.inkjay.simpleApi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getStudents(){
        return customerService.getCustomers();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<Customer>> getSingleMovie(@PathVariable UUID id){
        return new ResponseEntity<Optional<Customer>>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") UUID id ){
        customerService.deleteCustomer(id);
    }
    @PutMapping(path = "{id}")
    public void updateCustomer(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber){

       customerService.updateCustomer(id,firstName, lastName,email,phoneNumber);
    }



}
