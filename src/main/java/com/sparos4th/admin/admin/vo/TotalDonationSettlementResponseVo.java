package com.sparos4th.admin.admin.vo;

import com.sparos4th.admin.admin.dto.TotalDonationSettlementResponseDto;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TotalDonationSettlementResponseVo {

	private BigDecimal totalDonation;

	@Builder
	public TotalDonationSettlementResponseVo(BigDecimal totalDonation) {
		this.totalDonation = totalDonation;
	}

	public static TotalDonationSettlementResponseVo dtoToVo(
		TotalDonationSettlementResponseDto totalDonationSettlementResponseDto) {
		return TotalDonationSettlementResponseVo.builder()
			.totalDonation(totalDonationSettlementResponseDto.getTotalDonation()).build();
	}
}
