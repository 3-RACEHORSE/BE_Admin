package com.sparos4th.admin.admin.infrastructure.payment;

import com.sparos4th.admin.admin.domain.payment.TotalDonationSettlement;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalSettlementRepository extends JpaRepository<TotalDonationSettlement, Long> {

	TotalDonationSettlement findByTotalSettlementId(Long totalSettlementId);
}
