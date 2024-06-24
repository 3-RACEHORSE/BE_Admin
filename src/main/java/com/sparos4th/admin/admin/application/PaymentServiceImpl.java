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
	public List<PostDonationResponseDto> donationList() {

		List<Bank> donationList = bankRepository.findAll();

		return donationList.stream().map(PostDonationResponseDto::entityToDto).toList();
	}
}
