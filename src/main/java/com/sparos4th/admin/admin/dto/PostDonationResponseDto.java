package com.sparos4th.admin.admin.dto;

import com.sparos4th.admin.admin.domain.payment.Bank;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDonationResponseDto {
	private String auctionUuid;
	private BigDecimal donation;

	public static PostDonationResponseDto entityToDto(Bank bank)
	{
		return PostDonationResponseDto.builder()
			.auctionUuid(bank.getAuctionUuid())
			.donation(bank.getDonation())
			.build();
	}
}
