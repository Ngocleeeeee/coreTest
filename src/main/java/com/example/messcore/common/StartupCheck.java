package com.example.messcore.common;

import com.example.messcore.hotel.repository.HotelRepository;
import com.example.messcore.rabbitmq.listener.HotelQueueListenerManager;
import ezcloud.message.hotel.Hotel;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StartupCheck {

    private final HotelQueueListenerManager hotelQueueListenerManager;
    private final HotelRepository hotelRepository;
    @PostConstruct
    public void onStartup() {
        List<Hotel> activeHotels = hotelRepository.findAllByActiveNot((byte) 2);
        for (Hotel hotel : activeHotels) {
            hotelQueueListenerManager.startListeningForHotel(hotel.getId());
        }
    }
}
