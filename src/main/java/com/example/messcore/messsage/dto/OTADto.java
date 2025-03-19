package com.example.messcore.messsage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ezcloud.message.ota.OTA}
 */
@Getter
@Setter
public class OTADto implements Serializable {

    private String otaCode;
    private String code;
    private String nameVi;
    private String aliasVi;
    private int otaType;
    private String connection;
    private int connectionType;
}