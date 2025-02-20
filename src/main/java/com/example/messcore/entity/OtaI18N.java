package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ota_i18n")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class OtaI18N {
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

    @Column(name = "description")
    private String description;

    @Column(name = "languageCode")
    private String languageCode;

    @Column(name = "title")
    private String title;

    @Column(name = "alias")
    private String alias;

    @Column(name = "credentials")
    private String credentials;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "otaId")
    private UUID otaId;
}
