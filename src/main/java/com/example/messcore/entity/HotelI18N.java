package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hotel_i18n")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class HotelI18N {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "hotelId")
    private UUID hotelId;

    @Column(name = "languageCode")
    private String languageCode;

    @Column(name = "version")
    private Long version;

    @Column(name = "active")
    private Byte active;

    @Column(name = "createdBy")
    private UUID createdBy;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "sortIndex")
    private Integer sortIndex;

    @Column(name = "updatedBy")
    private UUID updatedBy;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "area")
    private String area;

    @Column(name = "location")
    private String location;

    @Column(name = "beFooter")
    private UUID beFooter;

    @Column(name = "highlight")
    private String highlight;
}
