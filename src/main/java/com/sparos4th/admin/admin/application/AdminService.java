package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.dto.AdminAddRequestDto;
import com.sparos4th.admin.admin.dto.AdminLoginRequestDto;
import com.sparos4th.admin.admin.dto.TokenResponseDto;
import org.springframework.stereotype.Service;

public interface AdminService {
	TokenResponseDto login(AdminLoginRequestDto adminLoginRequestDto);
	void addAdmin(AdminAddRequestDto adminAddRequestDto);
//	void changePassword(AdminChangePasswordRequestDto adminChangeP+

}
