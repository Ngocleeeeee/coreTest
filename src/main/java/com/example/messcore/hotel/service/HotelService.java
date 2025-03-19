package com.example.messcore.hotel.service;

import com.example.messcore.hotel.dto.HotelDto;
import com.example.messcore.hotel.mapper.HotelMapper;
import com.example.messcore.hotel.repository.HotelI18nRepository;
import com.example.messcore.hotel.repository.HotelRepository;
import com.example.messcore.rabbitmq.config.HotelQueueManager;
import com.example.messcore.rabbitmq.listener.HotelQueueListenerManager;
import ezcloud.message.hotel.Hotel;
import ezcloud.message.hotel.HotelI18n;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelI18nRepository hotelI18nRepository;
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final HotelQueueManager hotelQueueManager;
    private final HotelQueueListenerManager hotelQueueListenerManager;

    //update

    @Transactional
    public String createOrUpdateHotel(HotelDto hotelDto) {
        Optional<Hotel> existingHotelOpt = hotelRepository.findByHotelCode(hotelDto.getHotelCode());

        if (existingHotelOpt.isPresent()) {

            Hotel existingHotel = existingHotelOpt.get();
            hotelMapper.partialUpdate(hotelDto, existingHotel);
            hotelRepository.save(existingHotel);

            hotelI18nRepository.deleteByHotelId(existingHotel.getId());
            Set<HotelI18n> updatedHotelI18ns = hotelDto.getHotelI18n().stream()
                    .map(hotelMapper::toEntity)
                    .peek(hotelI18n -> hotelI18n.setHotel(existingHotel))
                    .collect(Collectors.toSet());
            hotelI18nRepository.saveAll(updatedHotelI18ns);

            return "Hotel updated successfully with UUID: " + existingHotel.getId();
        } else {

            Hotel hotel = hotelMapper.toEntity(hotelDto);
            hotel = hotelRepository.saveAndFlush(hotel);
            hotelI18nRepository.saveAll(hotel.getHotelI18n());

            hotelQueueManager.createQueueForHotel(hotel.getId());
            hotelQueueListenerManager.startListeningForHotel(hotel.getId());

            return "Hotel created successfully with UUID: " + hotel.getId();
        }
    }
    @Transactional
    public void updateAiStatus(UUID hotelId, Boolean isAutoReply) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isPresent()) {
            Hotel hotel = hotelOpt.get();
            hotel.setIsAutoReply(isAutoReply);
            hotelRepository.save(hotel);
        }
    }
}
