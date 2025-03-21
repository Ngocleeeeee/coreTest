package com.example.messcore.hotel.mapper;

import com.example.messcore.hotel.dto.HotelDto;
import com.example.messcore.hotel.dto.HotelI18nDto;
import ezcloud.message.hotel.Hotel;
import ezcloud.message.hotel.HotelI18n;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel toEntity(HotelDto hotelDto);
    HotelI18n toEntity(HotelI18nDto hotelI18nDto);

    @AfterMapping
    default void linkHotelI18n(@MappingTarget Hotel hotel) {
        hotel.getHotelI18n().forEach(hotelI18n -> hotelI18n.setHotel(hotel));
    }

    HotelDto toDto(Hotel hotel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Hotel partialUpdate(HotelDto hotelDto, @MappingTarget Hotel hotel);

    HotelDto toHotelDto(Hotel hotel);

    HotelI18nDto toHotelI18nDto(HotelI18n hotelDto);
}