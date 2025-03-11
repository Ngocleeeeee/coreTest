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
    public String loginAsGuest(String email) {
        String name = "Guest";

        Customer existingCustomer = customerRepository.findByEmail(email);
        if (existingCustomer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(email);
            newCustomer.setFirstName(name);
            newCustomer.setCustomerType(CustomerType.CUSTOMER);
            customerRepository.save(newCustomer);

        }
        return jwtService.generateToken(email, CustomerType.CUSTOMER.name());

    }

    @Transactional
    public String loginWithEmail(CustomerRequest request) {
        String email = request.getEmail();
        String name = request.getName();

        Customer existingCustomer = customerRepository.findByEmail(email);
        if (existingCustomer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(email);
            newCustomer.setFirstName(name);
            newCustomer.setCustomerType(CustomerType.CUSTOMER);
            customerRepository.save(newCustomer);
        }

        return jwtService.generateToken(email, CustomerType.CUSTOMER.name());
    }

    public String authenticateStaff(UserInfoEzId userInfo) {
        Customer customer = customerRepository.findByEmail(userInfo.getEmail());

        if (customer==null) {
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
