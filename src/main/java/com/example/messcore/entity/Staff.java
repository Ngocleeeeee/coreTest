package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "staff")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "staffCode")
    private String staffCode;

    @Column(name = "staffAlias")
    private String staffAlias;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "position")
    private String position;

    @Column(name = "descriptionVi")
    private String descriptionVi;

    @Column(name = "isOwner")
    private Boolean isOwner;

    @Column(name = "avatarUrl")
    private String avatarUrl;

    @Column(name = "ezUserId")
    private String ezUserId;

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

    @Column(name = "chatId")
    private String chatId;

    @Column(name = "description")
    private String description;

    @Column(name = "expiredVerify")
    private LocalDateTime expiredVerify;

    @Column(name = "isConfirmed")
    private Boolean isConfirmed;

    @Column(name = "isUneditable")
    private Boolean isUneditable;

    @Column(name = "lastlogin")
    private LocalDateTime lastlogin;

    @Column(name = "showNotification")
    private Byte showNotification;

    @Column(name = "verifier")
    private String verifier;

    @Column(name = "groupId")
    private UUID groupId;
}
