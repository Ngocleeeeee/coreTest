package com.example.messcore.review.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Attributes {
    public String content;
    public String guest_name;
    public String id;
    public String inserted_at;
    public boolean is_hidden;
    public boolean is_replied;
    public String ota;
    public String ota_reservation_id;
    public double overall_score;
    public String received_at;
    public Object reply;
    public ArrayList<Score> scores;
    public String updated_at;
}
