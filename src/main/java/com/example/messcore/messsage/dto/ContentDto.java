package com.example.messcore.messsage.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ContentDto implements Serializable {

    private UUID id;
    private String content;
    private String contentType;

}