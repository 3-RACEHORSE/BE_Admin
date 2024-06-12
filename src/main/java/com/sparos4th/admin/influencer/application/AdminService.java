package com.sparos4th.admin.influencer.application;

import com.sparos4th.admin.influencer.dto.InfluencerAddRequestDto;
import com.sparos4th.admin.influencer.dto.InfluencerDetailResponseDto;
import com.sparos4th.admin.influencer.dto.InfluencerUpdateRequestDto;

public interface AdminService {

	void addInfluencer(InfluencerAddRequestDto influencerAddRequestDto);

	InfluencerDetailResponseDto findInfluencer(String influencerUuid);

	void updateInfluencer(InfluencerUpdateRequestDto influencerUpdateRequestDto);

	void removeInfluencer(String influencerUuid);
}
