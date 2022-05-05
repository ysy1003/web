package com.ysy.pagedemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunAppInfo {
    private Integer id;
    private String userName;
    private String departName;
    private String pkgName;
    private String appName;
    private Timestamp runTime;
}
