package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "domain_staff_group")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class DomainStaffGroup {
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

    @Column(name = "descriptionVi")
    private String descriptionVi;

    @Column(name = "hotelId")
    private UUID hotelId;

    @Column(name = "chainId")
    private UUID chainId;

    @Column(name = "staffGroupId")
    private UUID staffGroupId;
}
