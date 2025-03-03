package com.example.messcore.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class HotelI18nDto implements Serializable {

    private UUID id;
    private String languageCode;
    private String title;
    private String address;
    private String city;
    private String country;
    private String description;
}