package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.dto.InfluencerAddRequestDto;
import com.sparos4th.admin.admin.dto.InfluencerDetailResponseDto;

public interface AdminService {

	void addInfluencer(InfluencerAddRequestDto influencerAddRequestDto);

	InfluencerDetailResponseDto findInfluencer(String influencerUuid);

	void updateInfluencer(InfluencerUpdateRequestDto influencerUpdateRequestDto);

	void removeInfluencer(String influencerUuid);
}
