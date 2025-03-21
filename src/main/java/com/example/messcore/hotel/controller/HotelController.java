package com.example.messcore.hotel.controller;

import com.example.messcore.common.dto.Res;
import com.example.messcore.hotel.dto.AiStatusUpdateRequest;
import com.example.messcore.hotel.dto.HotelDto;
import com.example.messcore.hotel.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/create")
    public Res createHotel(@RequestBody HotelDto hotelDto) {
        return new Res( Res.STATUS_OK,hotelService.createOrUpdateHotel(hotelDto));
    }

    @PutMapping("/updateAiStatus")
    public Res updateAiStatus(@RequestBody AiStatusUpdateRequest AiStatusUpdateRequest) {
        hotelService.updateAiStatus(AiStatusUpdateRequest.getHotelId(), AiStatusUpdateRequest.isAutoReply());

        return new Res(Res.STATUS_OK, AiStatusUpdateRequest);
    }
}