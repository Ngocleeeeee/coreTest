package com.example.messcore.customer.authen.service;

import com.example.messcore.customer.authen.dto.CustomerRequest;
import com.example.messcore.customer.authen.dto.UserInfoEzId;
import com.example.messcore.customer.repository.CustomerRepository;
import ezcloud.message.booking.Customer;
import ezcloud.message.booking.CustomerType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;

    public AuthService(CustomerRepository customerRepository, JwtService jwtService) {
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
    }
    @Transactional
    public String authenticateCustomerOrGuest(CustomerRequest userInfo) {
        Customer existingCustomer = customerRepository.findByEmail(userInfo.getEmail());

        if (existingCustomer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(userInfo.getEmail());
            newCustomer.setFirstName(userInfo.getName() != null ? userInfo.getName() : "Guest");

            newCustomer.setCustomerType(userInfo.getType());

            customerRepository.save(newCustomer);
            existingCustomer = newCustomer;
        }

        return jwtService.generateToken(existingCustomer.getEmail(), existingCustomer.getCustomerType().name());
    }

    public String authenticateStaff(UserInfoEzId userInfo) {
        Customer customer = customerRepository.findByEmail(userInfo.getEmail());

        if (customer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setFirstName(userInfo.getSub());
            newCustomer.setEmail(userInfo.getEmail());
            newCustomer.setLastName(userInfo.getName());
            newCustomer.setCustomerType(CustomerType.STAFF);
            newCustomer.setBirthdate(userInfo.getBirthdate());
            customerRepository.save(newCustomer);
        }

        return jwtService.generateToken(userInfo.getEmail(), CustomerType.STAFF.name());
    }


}
