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
    public String authenticate(UserInfoEzId userInfo) {
        Customer existingCustomer = customerRepository.findByEmail(userInfo.getEmail());

        if (existingCustomer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(userInfo.getEmail());
            newCustomer.setLastName(userInfo.getType() == 1 ? userInfo.getName() : null); // Staff có lastName
            newCustomer.setBirthdate(userInfo.getBirthdate());
            newCustomer.setPropertyId(userInfo.getPropertyId());
            // Xác định loại người dùng dựa trên `type`
            switch (userInfo.getType()) {
                case 1:
                    newCustomer.setFirstName(userInfo.getName());
                    newCustomer.setCustomerType(CustomerType.STAFF);
                    break;
                case 2:
                    newCustomer.setFirstName("GUEST");
                    newCustomer.setCustomerType(CustomerType.GUEST);
                    break;
                default:
                    newCustomer.setFirstName(userInfo.getName());
                    newCustomer.setCustomerType(CustomerType.CUSTOMER);
                    break;
            }

            customerRepository.save(newCustomer);
            existingCustomer = newCustomer;
        }

        return jwtService.generateToken(existingCustomer.getEmail(), existingCustomer.getCustomerType().name());
    }

}
