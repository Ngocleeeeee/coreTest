package com.example.messcore.customer.authen.filter;

import com.example.messcore.customer.authen.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class JwtChannelInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // Lấy token từ header Authorization
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader("Authorization");
            String propertyCode = accessor.getFirstNativeHeader("Property-Code");

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            if (token != null && jwtService.validateToken(token)) {
                Claims claims = jwtService.extractClaims(token);
                accessor.getSessionAttributes().put("user", claims.getSubject());
                accessor.getSessionAttributes().put("token", token); // Lưu token vào session
//                accessor.getSessionAttributes().put("propertyCode", propertyCode); // Lưu hotelCode vào session
//                System.out.println("✅ Lưu token vào session: " + token);

                Principal userPrincipal = claims::getSubject;

                accessor.setUser(userPrincipal);
            } else {
                System.out.println("❌ Token không hợp lệ khi CONNECT");
            }
        } else {
            // Giữ Principal cho các tin nhắn tiếp theo
            if (accessor.getUser() == null) {
                Object user = accessor.getSessionAttributes().get("user");
                if (user != null) {
                    accessor.setUser(new Principal() {
                        @Override
                        public String getName() {
                            return user.toString();
                        }
                    });
                }
            }
        }

        return message;
    }
}
