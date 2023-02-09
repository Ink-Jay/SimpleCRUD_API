package com.inkjay.simpleApi.customer;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.email= ?1")
    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findById(UUID id);

    boolean existsById(UUID id);

    @Transactional
    void deleteById(UUID id);

//    Optional<Customer> deleteById(UUID id);
}
