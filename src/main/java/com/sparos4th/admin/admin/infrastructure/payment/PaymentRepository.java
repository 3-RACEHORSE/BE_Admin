package com.sparos4th.admin.admin.infrastructure.payment;

import com.sparos4th.admin.admin.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
