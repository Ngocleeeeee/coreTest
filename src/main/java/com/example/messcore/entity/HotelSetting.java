package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hotel_setting")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class HotelSetting {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hotelId")
    private UUID hotelId;

    @Column(name = "hotelAiSwitch")
    private Boolean hotelAiSwitch;

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
}
