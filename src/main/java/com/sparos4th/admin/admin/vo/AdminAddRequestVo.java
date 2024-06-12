package com.sparos4th.admin.admin.vo;

import com.sparos4th.admin.common.AdminGrant;
import lombok.Getter;

@Getter
public class AdminAddRequestVo {
    private String email;
    private String password;
    private String name;
    private AdminGrant grant;
}
