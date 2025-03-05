package com.example.messcore.customer.authen.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RecaptchaService {

    private final String secretKey;
    private final String verifyUrl;
    private final RestTemplate restTemplate;

    public RecaptchaService(@Value("${recaptcha.secretKey}") String secretKey,
                            @Value("${recaptcha.verifyUrl}") String verifyUrl) {
        this.secretKey = secretKey;
        this.verifyUrl = verifyUrl;
        this.restTemplate = new RestTemplate();
    }

    public boolean validateRecaptcha(String recaptchaToken) {
        String url = verifyUrl + "?secret=" + secretKey + "&response=" + recaptchaToken;

        Map response = restTemplate.postForObject(url, null, Map.class);
        if (response == null || !Boolean.TRUE.equals(response.get("success"))) {
            return false;
        }

        double score = (double) response.getOrDefault("score", 0.0);

        return score >= 0.5; // Chỉ chấp nhận nếu score đủ cao (chống bot)
    }
}
