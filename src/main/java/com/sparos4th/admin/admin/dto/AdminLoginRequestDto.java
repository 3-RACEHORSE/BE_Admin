package com.sparos4th.admin.admin.dto;

import com.sparos4th.admin.admin.vo.AdminLoginRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLoginRequestDto {

	private String email;
	private String password;

    public static AdminLoginRequestDto voToDto(AdminLoginRequestVo adminLoginRequestVo) {
        return AdminLoginRequestDto.builder()
            .email(adminLoginRequestVo.getEmail())
            .password(adminLoginRequestVo.getPassword())
            .build();
    }
}
