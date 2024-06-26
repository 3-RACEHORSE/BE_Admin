package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.domain.payment.TotalDonationSettlement;
import com.sparos4th.admin.admin.dto.PostDonationResponseDto;
import com.sparos4th.admin.admin.dto.TotalDonationSettlementResponseDto;
import java.util.List;

public interface PaymentService {

	List<PostDonationResponseDto> donationList();
	TotalDonationSettlementResponseDto totalDonation();
}
