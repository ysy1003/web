package com.ysy.pagedemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppBean {
    private int id;
    private String dvMac;
    private String dvName;
    private String osName;
    private Integer userId;
    private String userName;
    private String userEmail;

    private String appName;
    private String pgName;
    private Integer isEnable; //判断是否可用
}
