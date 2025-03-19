package com.example.messcore.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Message {

    private UUID id;
    private String content;
    private String time;
}
