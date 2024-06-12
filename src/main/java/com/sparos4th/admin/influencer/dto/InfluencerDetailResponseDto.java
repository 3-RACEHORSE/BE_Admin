package com.sparos4th.admin.influencer.dto;

import com.sparos4th.admin.influencer.vo.InfluencerDetailResponseVo;
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
public class InfluencerDetailResponseDto {

	private String influencerUuid;
	private String name;
	private String profileImage;
	private String phoneNum;
	private String description;

	public static InfluencerDetailResponseVo dtoToVo(
		InfluencerDetailResponseDto influencerDetailResponseDto) {
		return new InfluencerDetailResponseVo(
			influencerDetailResponseDto.getInfluencerUuid(),
			influencerDetailResponseDto.getName(),
			influencerDetailResponseDto.getPhoneNum(),
			influencerDetailResponseDto.getProfileImage(),
			influencerDetailResponseDto.getDescription()
		);
	}
}
