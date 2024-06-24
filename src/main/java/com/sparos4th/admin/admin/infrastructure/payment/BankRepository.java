package com.sparos4th.admin.admin.infrastructure.payment;

import com.sparos4th.admin.admin.domain.payment.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}
