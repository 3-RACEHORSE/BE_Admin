package com.sparos4th.admin.admin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Influencer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "influencer_id")
	private Long id;
	@Column(name = "influencer_uuid", nullable = false)
	private String uuid;
	@Column(name = "name", nullable = false, length = 40)
	private String name;
	@Column(name = "profile_image", nullable = false)
	private String profileImage;
	@Column(name = "phone_num", nullable = false, length = 20)
	private String phoneNum;
	@Column(name = "description")
	private String description;

	@Builder
	public Influencer(Long id, String uuid, String name, String profileImage, String phoneNum, String description) {
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.profileImage = profileImage;
		this.phoneNum = phoneNum;
		this.description = description;
	}
}
