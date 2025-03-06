package com.example.messcore.hotel.controller;

import com.example.messcore.hotel.dto.HotelDto;
import com.example.messcore.hotel.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class CreateHotelAndHotelQueue {

    private final HotelService hotelService;

    @PostMapping("/create")
    public String createHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.createHotel(hotelDto);
    }
}
