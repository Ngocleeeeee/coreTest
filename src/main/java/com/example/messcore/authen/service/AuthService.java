package com.example.messcore.authen.service;

import com.example.messcore.authen.dto.CustomerRequest;
import com.example.messcore.authen.utils.IPUtils;
import com.example.messcore.repository.CustomerRepository;
import ezcloud.message.booking.Customer;
import ezcloud.message.booking.CustomerType;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final NewCustomerNotificationService notificationService;

    @Autowired
    private OtpService otpService;

    public AuthService(CustomerRepository customerRepository, JwtService jwtService, NewCustomerNotificationService notificationService) {
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.notificationService = notificationService;
    }

    private final Map<String, CompletableFuture<Boolean>> pendingRegistrations = new ConcurrentHashMap<>();


    @Transactional
    public String loginAsGuest(HttpServletRequest request) {
        String ip = IPUtils.getClientIP(request);
        String email = ip + "@guest.khachhang.com.vn";
        String name = "Guest";

        Customer existingCustomer = customerRepository.findByEmail(email);
        if (existingCustomer == null) {
//            CompletableFuture<Boolean> future = new CompletableFuture<>();
//            pendingRegistrations.put(email, future);

            notificationService.sendGuestNotification(email, name, CustomerType.GUEST);//            Customer newCustomer = new Customer();
            Customer newCustomer = new Customer();
            newCustomer.setEmail(email);
            newCustomer.setFirstName("Guest");
            newCustomer.setCustomerType(CustomerType.GUEST);
            customerRepository.save(newCustomer);
//            try {
//                // Chờ phản hồi từ messageCore (timeout sau 5 giây)
//                Boolean isRegistered = future.get(5, TimeUnit.SECONDS);
//                if (!isRegistered) {
//                    throw new RuntimeException("Không thể đăng nhập vì tài khoản chưa được tạo.");
//                }
//            } catch (Exception e) {
//                throw new RuntimeException("Lỗi khi xác thực tài khoản: " + e.getMessage());
//            }
        }
        return jwtService.generateToken(email, CustomerType.GUEST.name());

    }

//    @RabbitListener(queues = "queueNotifiIn")
//    public void handleRegistrationRequest(GuestCustomerNotification notification) {
//        String email = notification.getEmail();
//        String name = notification.getName();
//        CustomerType role = notification.getRole();
//        Customer newCustomer = new Customer();
//        newCustomer.setEmail(email);
//        newCustomer.setFirstName(name);
//        newCustomer.setCustomerType(role);
//        customerRepository.save(newCustomer);
//        rabbitTemplate.convertAndSend("queueNotifiOut", Map.of("email", email, "success", true));
//
//    }

    @RabbitListener(queues = "queueNotifiOut")
    public void handleRegistrationResponse(@Payload Map<String, String> message) {
        String email = message.get("email");
        Boolean success = Boolean.parseBoolean(message.get("success"));

        CompletableFuture<Boolean> future = pendingRegistrations.remove(email);
        if (future != null) {
            future.complete(success);
        }
    }

    @Transactional
    public String loginWithEmail(String otp, CustomerRequest request) {
        String email = request.getEmail();
        String name = request.getName();

        if (!otpService.validateOtp(email, otp)) {
            throw new RuntimeException("Mã OTP không hợp lệ hoặc đã hết hạn.");
        }

        Customer existingCustomer = customerRepository.findByEmail(email);
        if (existingCustomer == null) {
            notificationService.sendGuestNotification(email, name, CustomerType.USER);
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            pendingRegistrations.put(email, future);
//            Customer newCustomer = new Customer();
//            newCustomer.setEmail(email);
//            newCustomer.setFirstName(name);
//            newCustomer.setCustomerType(CustomerType.USER);
//            customerRepository.save(newCustomer);
            try {
                // Chờ phản hồi từ messageCore (timeout sau 5 giây)
                Boolean isRegistered = future.get(5, TimeUnit.SECONDS);
                if (!isRegistered) {
                    throw new RuntimeException("Không thể đăng nhập vì tài khoản chưa được tạo.");
                }
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi xác thực tài khoản: " + e.getMessage());
            }
        }

        otpService.removeOtp(email);

        return jwtService.generateToken(email, CustomerType.USER.name());
    }

}
