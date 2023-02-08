package com.inkjay.simpleApi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping
    public void registerNewStudent(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long id ){
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
