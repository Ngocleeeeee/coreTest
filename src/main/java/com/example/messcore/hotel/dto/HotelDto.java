package com.example.messcore.hotel.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class HotelDto implements Serializable {

    private byte active;
    private String externalCode;
    private String externalHotelId;
    private String hotelCode;
    private String nameVi;
    private String addressVi;
    private String cityVi;
    private String countryVi;
    private String descriptionVi;
    private String logoUrl;
    private String email;
    private String phone;
    private String defaultImageUrl;
    private Set<HotelI18nDto> hotelI18n;

}