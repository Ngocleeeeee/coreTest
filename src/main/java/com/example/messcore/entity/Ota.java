package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ota")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class Ota {
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

    @Column(name = "aliasVi")
    private String aliasVi;

    @Column(name = "connection")
    private String connection;

    @Column(name = "credentialsVi")
    private String credentialsVi;

    @Column(name = "descriptionVi")
    private String descriptionVi;

    @Column(name = "instructionVi")
    private String instructionVi;

    @Column(name = "nameVi")
    private String nameVi;

    @Column(name = "otaCode")
    private String otaCode;

    @Column(name = "otaType")
    private Integer otaType;

    @Column(name = "iconUrl")
    private String iconUrl;

    @Column(name = "connectionType")
    private Integer connectionType;

    @Column(name = "otaGroupId")
    private UUID otaGroupId;

    @Column(name = "code")
    private String code;
}
