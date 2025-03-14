package com.example.messcore.messsage.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@Slf4j
public class FirebaseConfig {
    private static final String FIREBASE_CONFIG_PATH = "/ezmessage-test-firebase-adminsdk-fbsvc-d61c4bfc19.json";

    @PostConstruct
    public void init() {
        try (InputStream serviceAccount = getClass().getResourceAsStream(FIREBASE_CONFIG_PATH)) {
            if (serviceAccount == null) {
                throw new IOException("Không tìm thấy file cấu hình Firebase: " + FIREBASE_CONFIG_PATH);
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase đã được khởi tạo thành công với file cấu hình: {}", FIREBASE_CONFIG_PATH);
            }
        } catch (IOException e) {
            log.error("Lỗi khi khởi tạo Firebase: {}", e.getMessage());
        }
    }
}
