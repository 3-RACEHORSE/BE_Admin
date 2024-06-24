package com.sparos4th.admin.admin.presentation;

import com.sparos4th.admin.admin.application.PaymentService;
import com.sparos4th.admin.admin.vo.PostDonationResponseVo;
import com.sparos4th.admin.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping("/donationlist")
	@Operation(summary = "기부금 내역 조회", description = "기부금 내역 조회")
	public SuccessResponse<List<PostDonationResponseVo>> PostDonationList() {
		return new SuccessResponse<>(
			(paymentService.donationList()).stream().map(PostDonationResponseVo::dtoToVo).toList());
	}

}
