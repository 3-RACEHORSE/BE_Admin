package com.sparos4th.admin.admin.presentation;

import com.sparos4th.admin.admin.application.AdminService;
import com.sparos4th.admin.admin.dto.AdminAddRequestDto;
import com.sparos4th.admin.admin.dto.AdminLoginRequestDto;
import com.sparos4th.admin.admin.dto.TokenResponseDto;
import com.sparos4th.admin.admin.vo.AdminAddRequestVo;
import com.sparos4th.admin.admin.vo.AdminLoginRequestVo;
import com.sparos4th.admin.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "관리자", description = "관리자 관리 API")
@RequestMapping("/api/v1/admin")
public class adminController {
    private final AdminService adminService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입")
    public SuccessResponse<Object> snsAddMember(
        @RequestBody AdminAddRequestVo adminAddRequestVo) {
        log.info(adminAddRequestVo.toString());
        adminService.addAdmin(AdminAddRequestDto.voToDto(adminAddRequestVo));
        return new SuccessResponse<>(null);
    }
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인")
    public ResponseEntity<SuccessResponse<Object>> login(
        @RequestBody AdminLoginRequestVo adminLoginRequestVo) {
        TokenResponseDto tokenResponseDto = adminService.login(
            AdminLoginRequestDto.voToDto(adminLoginRequestVo));

        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, tokenResponseDto.getAccessToken())
            .body(new SuccessResponse<>(null));
    }
}
