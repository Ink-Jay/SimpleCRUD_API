package com.inkjay.simpleApi.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner (CustomerRepository repository){
        return args -> {
            Customer Jane = new Customer(
                    "Jane",
                    "Doe",
                    "janedoe@gmail.com",
                    "0712345678"
            );
            Customer John = new Customer(
                    "John",
                    "Doe",
                    "johndoe@gmail.com",
                    "0787654321"
            );
            repository.saveAll(
                    List.of(Jane, John)
            );
        };
    }

}
