package com.example.messcore.review.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Data {

    public UUID id;
    public String type;
    public Attributes attributes;
    public Relationships relationships;
}
