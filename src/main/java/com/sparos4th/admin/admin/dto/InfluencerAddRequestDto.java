package com.sparos4th.admin.admin.dto;

import com.sparos4th.admin.admin.vo.InfluencerAddRequestVo;
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
public class InfluencerAddRequestDto {

	private String name;
	private String profileImage;
	private String phoneNum;
	private String description;

	public static InfluencerAddRequestDto voToDto(
		InfluencerAddRequestVo influencerAddRequestVo) {
		return InfluencerAddRequestDto.builder()
			.name(influencerAddRequestVo.getName())
			.profileImage(influencerAddRequestVo.getProfileImage())
			.phoneNum(influencerAddRequestVo.getPhoneNum())
			.description(influencerAddRequestVo.getDescription())
			.build();
	}
}
