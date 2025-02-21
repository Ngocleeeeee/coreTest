package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "permission")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
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

    @Column(name = "descriptionVi")
    private String descriptionVi;

    @Column(name = "permissionCode")
    private String permissionCode;

    @Column(name = "nameVi")
    private String nameVi;

    @Column(name = "functionsId")
    private UUID functionsId;

    @Column(name = "permissionLevel")
    private String permissionLevel;
}
