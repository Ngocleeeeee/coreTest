package com.example.messcore.repository;

import ezcloud.message.booking.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByEmail(String email);

    Customer findCustomerById(UUID uuid);
}
