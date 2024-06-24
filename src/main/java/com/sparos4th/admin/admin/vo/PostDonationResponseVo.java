package com.sparos4th.admin.admin.vo;

import com.sparos4th.admin.admin.dto.PostDonationResponseDto;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDonationResponseVo {
	private String auctionUuid;
	private BigDecimal donation;

	@Builder
	public PostDonationResponseVo(String auctionUuid, BigDecimal donation){
		this.auctionUuid = auctionUuid;
		this.donation = donation;
	}

	public static PostDonationResponseVo dtoToVo(PostDonationResponseDto postDonationResponseDto) {
		return PostDonationResponseVo.builder()
			.auctionUuid(postDonationResponseDto.getAuctionUuid())
			.donation(postDonationResponseDto.getDonation())
			.build();
	}
}
