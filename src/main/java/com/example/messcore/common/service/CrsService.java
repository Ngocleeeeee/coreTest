package com.example.messcore.common.service;

import com.example.messcore.common.entity.ResCrsAdmin;
import com.example.messcore.hotel.repository.HotelRepository;
import com.example.messcore.messsage.dto.BookingDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CrsService {

    @Value("${crs.admin-url}")
    private String adminCrsUrl;
    private final RestTemplate restTemplate;
    private final Gson gson;
    public static HttpHeaders headers;

    {
        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public CrsService(RestTemplate restTemplate, Gson gson, HotelRepository hotelRepository) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    public BookingDto getBookingCrs(String bookingId) {
        Map<String, String> param = new HashMap<>();
        param.put("id", bookingId);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<ResCrsAdmin> response = restTemplate.exchange(adminCrsUrl + "booking/detailByExternalId?id={id}", HttpMethod.GET, entity,
                ResCrsAdmin.class, param);
        if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) return null;
        return gson.fromJson(gson.toJson(response.getBody().getValue()), new TypeToken<BookingDto>() {
        }.getType());
    }
}
