package com.example.messcore.controller;


import com.example.messcore.dto.HotelDto;
import com.example.messcore.mapper.HotelMapper;
import com.example.messcore.queue.HotelQueueListenerManager;
import com.example.messcore.queue.HotelQueueManager;
import com.example.messcore.repository.HotelI18nRepository;
import com.example.messcore.repository.HotelRepository;
import ezcloud.message.hotel.Hotel;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class CreateHotelAndHotelQueue {
    private final HotelI18nRepository hotelI18nRepository;
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final HotelQueueManager hotelQueueManager;
    private final HotelQueueListenerManager hotelQueueListenerManager;

    @PostMapping("/create")
    @Transactional
    public String createHotel(@RequestBody HotelDto hotelDto) {
        // Chuyển DTO thành Entity
        Hotel hotel = hotelMapper.toEntity(hotelDto);

        // Lưu vào database
        hotel = hotelRepository.saveAndFlush(hotel);
        hotelI18nRepository.saveAll(hotel.getHotelI18n());
        // Tạo queue mới theo hotelId
        hotelQueueManager.createQueueForHotel(hotel.getId());
        hotelQueueListenerManager.startListeningForHotel(hotel.getId());
        return "Hotel created successfully with UUID: " + hotel.getId();
    }

}
