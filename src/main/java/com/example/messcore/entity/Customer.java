package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class Customer {
    @Id
    @Column(name = "id")
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

    @Column(name = "customerCode")
    private String customerCode;

    @Column(name = "customerAlias")
    private String customerAlias;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "title")
    private String title;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthdate")
    private LocalDateTime birthdate;

    @Column(name = "identity")
    private String identity;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "cityVi")
    private String cityVi;

    @Column(name = "countryVi")
    private String countryVi;

    @Column(name = "countryCode2")
    private String countryCode2;

    @Column(name = "countryCode3")
    private String countryCode3;

    @Column(name = "descriptionVi")
    private String descriptionVi;

    @Column(name = "otherInfo")
    private String otherInfo;

    @Column(name = "country")
    private String country;

    @Column(name = "faceId")
    private String faceId;
}
