package com.sparos4th.admin.admin.dto;

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
}
