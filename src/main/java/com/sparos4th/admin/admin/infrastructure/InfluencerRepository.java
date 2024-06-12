package com.sparos4th.admin.admin.infrastructure;

import com.sparos4th.admin.admin.domain.Influencer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
	Optional<Influencer> findByUuid(String uuid);
	Optional<Influencer> findByName(String name);
	Optional<Influencer> findByPhoneNum(String phoneNum);
}
