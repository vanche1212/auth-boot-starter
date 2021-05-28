package com.zrtg.auth.core;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Permission {

    private Integer id;
    private Integer parentId;
    private String permissionName;
    private String permissionCode;
    private String permissionMethod;
    private String permissionPath;
    private Integer permissionType;
    private String remark;
    private LocalDateTime created;
    private LocalDateTime updated;


}
