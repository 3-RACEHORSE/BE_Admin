package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.dto.PostDonationResponseDto;
import java.util.List;

public interface PaymentService {

	List<PostDonationResponseDto> donationList();
}
