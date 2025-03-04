package com.example.messcore.config;

import com.example.messcore.queue.HotelQueueListenerManager;
import com.example.messcore.repository.HotelRepository;
import ezcloud.message.hotel.Hotel;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
