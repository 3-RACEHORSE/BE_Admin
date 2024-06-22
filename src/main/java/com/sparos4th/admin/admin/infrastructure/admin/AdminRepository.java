package com.sparos4th.admin.admin.infrastructure.admin;

import com.sparos4th.admin.admin.domain.admin.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<Admin> findByUuid(String uuid);
	Optional<Admin> findByEmail(String email);
}
