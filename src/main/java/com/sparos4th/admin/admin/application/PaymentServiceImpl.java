package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.domain.payment.Bank;
import com.sparos4th.admin.admin.dto.PostDonationResponseDto;
import com.sparos4th.admin.admin.infrastructure.payment.BankRepository;
import com.sparos4th.admin.admin.vo.PostDonationResponseVo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private final BankRepository bankRepository;

	@Override
	public List<PostDonationResponseVo> donationList() {
		List<PostDonationResponseDto> postDonationResponseDtoList = new ArrayList<>();

		List<Bank> donationList = bankRepository.findAll();

		donationList.stream()
			.map(bank ->
				PostDonationResponseDto.builder()
					.auctionUuid(bank.getAuctionUuid())
					.donation(bank.getDonation())
					.build())
			.forEach(postDonationResponseDtoList::add);

		List<PostDonationResponseVo> donationResponseVoList = new ArrayList<>();

		postDonationResponseDtoList.stream()
			.map(dto -> PostDonationResponseVo.builder()
				.auctionUuid(dto.getAuctionUuid())
				.donation(dto.getDonation())
				.build())
			.forEach(donationResponseVoList::add);

		return donationResponseVoList;
	}
}
