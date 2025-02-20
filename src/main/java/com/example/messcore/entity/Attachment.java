package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attachment")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "messageId")
    private UUID messageId;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileExtension")
    private String fileExtension;

    @Column(name = "fileURL")
    private String fileUrl;

    @Column(name = "size")
    private Double size;

    @Column(name = "sizeUnit")
    private String sizeUnit;

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
