package com.lk.javaweb.enity;

import lombok.Data;

@Data
public class AdminInfo {
    private Integer adminId;
    private String adminName;
    private String adminPass;
    private Integer adminStyle;
    private String adminEmail;
}
