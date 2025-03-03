package com.example.messcore.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResCrsAdmin {

    public static final int STATUS_OK = 0;
    public static final int STATUS_ERR = 1;

    private int responseCode;
    private Object value;
    private String processId;

}

