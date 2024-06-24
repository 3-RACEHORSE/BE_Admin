package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.vo.PostDonationResponseVo;
import java.util.List;

public interface PaymentService {
	List<PostDonationResponseVo> donationList();
}
