package com.sparos4th.admin.admin.presentation;

import com.sparos4th.admin.admin.application.AdminService;
import com.sparos4th.admin.admin.dto.InfluencerAddRequestDto;
import com.sparos4th.admin.admin.dto.InfluencerDetailResponseDto;
import com.sparos4th.admin.admin.dto.InfluencerUpdateRequestDto;
import com.sparos4th.admin.admin.vo.InfluencerAddRequestVo;
import com.sparos4th.admin.admin.vo.InfluencerDetailResponseVo;
import com.sparos4th.admin.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

	private final AdminService adminService;

	@PostMapping("/add")
	public SuccessResponse<Object> addInfluencer(
		@RequestBody InfluencerAddRequestVo influencerAddRequestVo) {
		adminService.addInfluencer(InfluencerAddRequestDto.voToDto(influencerAddRequestVo));
		return new SuccessResponse<>(null);
	}

	@GetMapping("/influencer")
	public SuccessResponse<InfluencerDetailResponseVo> findInfluencer(
		@RequestParam("influencerId") String influencerUuid) {
		return new SuccessResponse<>(
			InfluencerDetailResponseDto.dtoToVo(adminService.findInfluencer(influencerUuid)));
	}
}
