package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.domain.payment.Bank;
import com.sparos4th.admin.admin.domain.payment.TotalDonationSettlement;
import com.sparos4th.admin.admin.dto.PostDonationResponseDto;
import com.sparos4th.admin.admin.dto.TotalDonationSettlementResponseDto;
import com.sparos4th.admin.admin.infrastructure.payment.BankRepository;
import com.sparos4th.admin.admin.infrastructure.payment.TotalSettlementRepository;
import com.sparos4th.admin.common.exception.CustomException;
import com.sparos4th.admin.common.exception.ResponseStatus;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private final BankRepository bankRepository;
	private final TotalSettlementRepository totalSettlementRepository;

	@Override
	public List<PostDonationResponseDto> donationList() {

		List<Bank> donationList = bankRepository.findAll();

		return donationList.stream().map(PostDonationResponseDto::entityToDto).toList();
	}

	@Override
	public TotalDonationSettlementResponseDto totalDonation() {

		TotalDonationSettlement totalDonationSettlement = totalSettlementRepository.findById(
			1L).orElseThrow(() -> new CustomException(ResponseStatus.NO_SUCH_ELEMENT));

		return TotalDonationSettlementResponseDto.builder()
			.totalDonation(totalDonationSettlement.getTotalDonation())
			.build();
	}
}
