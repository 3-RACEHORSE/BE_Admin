package com.sparos4th.admin.influencer.dto;

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
public class InfluencerUpdateResponseDto {

	private String influencerUuid;
	private String name;
	private String phoneNum;
	private String profileImage;
	private String description;

	public static InfluencerUpdateResponseDto voToDto(
		String influencerUuid, String name, String phoneNum, String profileImage, String description) {
		return InfluencerUpdateResponseDto.builder()
			.influencerUuid(influencerUuid)
			.name(name)
			.phoneNum(phoneNum)
			.profileImage(profileImage)
			.description(description)
			.build();
	}
}
