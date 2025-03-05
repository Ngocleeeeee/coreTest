package com.example.messcore.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Res {

    public static final int STATUS_OK = 0;
    public static final int STATUS_ERR = 1;

    private int responseCode;
    private Object data;

    public Res(int statusErr, String data) {
        this.responseCode = statusErr;
        this.data = data;
    }
}
