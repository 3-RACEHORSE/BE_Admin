package com.sparos4th.admin.admin.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class TotalDonationSettlementResponseDto {

	private BigDecimal totalDonation;

	@Builder
	public TotalDonationSettlementResponseDto(BigDecimal totalDonation){
		this.totalDonation = totalDonation;
	}
}
