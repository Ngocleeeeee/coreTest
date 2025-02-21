package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hotel")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class Hotel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @Column(name = "externalHotelId")
    private String externalHotelId;

    @Column(name = "externalCode")
    private String externalCode;

    @Column(name = "hotelCode")
    private String hotelCode;

    @Column(name = "nameVi")
    private String nameVi;

    @Column(name = "addressVi")
    private String addressVi;

    @Column(name = "cityVi")
    private String cityVi;

    @Column(name = "countryVi")
    private String countryVi;

    @Column(name = "descriptionVi")
    private String descriptionVi;

    @Column(name = "logoUrl")
    private String logoUrl;

    @Column(name = "defaultImageUrl")
    private String defaultImageUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;
}
