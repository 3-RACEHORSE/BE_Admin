package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.domain.Influencer;
import com.sparos4th.admin.admin.dto.InfluencerAddRequestDto;
import com.sparos4th.admin.admin.dto.InfluencerDetailResponseDto;
import com.sparos4th.admin.admin.infrastructure.InfluencerRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

	private InfluencerRepository influencerRepository;

	@Override
	public void addInfluencer(InfluencerAddRequestDto influencerAddRequestDto) {
		influencerRepository.findByPhoneNum(influencerAddRequestDto.getPhoneNum())
			.ifPresent(influencer -> {
				throw new IllegalArgumentException("이미 등록된 전화번호입니다.");
			});

		String influencerUuid = "Influencer" + UUID.randomUUID().toString();

		Influencer influencer = Influencer.builder()
			.uuid(influencerUuid)
			.name(influencerAddRequestDto.getName())
			.phoneNum(influencerAddRequestDto.getPhoneNum())
			.profileImage(influencerAddRequestDto.getProfileImage())
			.description(influencerAddRequestDto.getDescription())
			.build();

		influencerRepository.save(influencer);
	}

	@Override
	public InfluencerDetailResponseDto findInfluencer(String influencerUuid) {
		Influencer influencer = influencerRepository.findByUuid(influencerUuid)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 인플루언서입니다."));

		return InfluencerDetailResponseDto.builder()
			.influencerUuid(influencer.getUuid())
			.name(influencer.getName())
			.phoneNum(influencer.getPhoneNum())
			.profileImage(influencer.getProfileImage())
			.description(influencer.getDescription())
			.build();
	}

	@Override
	public void updateInfluencer(InfluencerUpdateRequestDto influencerUpdateRequestDto) {

	}

	@Override
	public void removeInfluencer(String influencerUuid) {

	}
}
