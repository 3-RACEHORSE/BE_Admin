package com.sparos4th.admin.admin.presentation;

import com.sparos4th.admin.admin.application.AdminService;
import com.sparos4th.admin.admin.dto.AdminAddRequestDto;
import com.sparos4th.admin.admin.vo.AdminAddRequestVo;
import com.sparos4th.admin.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "관리자", description = "관리자 관리 API")
@RequestMapping("/api/v1/admin")
public class adminController {
    private final AdminService adminService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입")
    public SuccessResponse<Object> snsAddMember(
        @RequestBody AdminAddRequestVo adminAddRequestVo,
        @RequestHeader String token) {
        log.info(adminAddRequestVo.toString());
        adminService.addAdmin(AdminAddRequestDto.voToDto(adminAddRequestVo), token);
        return new SuccessResponse<>(null);
    }
}
