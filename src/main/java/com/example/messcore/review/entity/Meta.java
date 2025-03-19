package com.example.messcore.review.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {
    public int total;
    public int limit;
    public String order_by;
    public int page;
    public String order_direction;
}
