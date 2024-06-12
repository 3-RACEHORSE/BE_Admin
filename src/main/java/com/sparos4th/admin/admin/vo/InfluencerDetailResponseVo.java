package com.sparos4th.admin.admin.vo;

import lombok.Getter;

@Getter
public class InfluencerDetailResponseVo {

	private String influencerUuid;
	private String name;
	private String phoneNum;
	private String profileImage;
	private String description;

	public InfluencerDetailResponseVo(String influencerUuid, String name, String phoneNum, String profileImage, String description) {
		this.influencerUuid = influencerUuid;
		this.name = name;
		this.phoneNum = phoneNum;
		this.profileImage = profileImage;
		this.description = description;
	}
}
