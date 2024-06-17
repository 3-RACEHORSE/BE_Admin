package com.sparos4th.admin.admin.infrastructure;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RefreshTokenCertification {

    @Value("${JWT.REFRESH_TOKEN_EXPIRATION_TIME}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    private final String PREFIX = "REFRESH:";

    private final StringRedisTemplate stringRedisTemplate;

    //refreshToken을 받아서 redis에 저장
    public void saveRefreshToken(String uuid, String refreshToken) {
        try {
            long refreshTokenExpirationSeconds = REFRESH_TOKEN_EXPIRATION_TIME / 1000; // 밀리초를 초로 변환
            stringRedisTemplate.opsForValue()
                .set(uuid, refreshToken, Duration.ofSeconds(refreshTokenExpirationSeconds));
            log.info("Successfully saved refreshToken to Redis: uuid={}, refreshToken={}", uuid, refreshToken);
        } catch (Exception e) {
            log.error("Failed to save refreshToken to Redis: uuid={}, refreshToken={}", uuid, refreshToken, e);
            throw new RuntimeException("Failed to save refreshToken to Redis", e);
        }
    }


    //uuid로 refreshToken을 가져옴
    public String getRefreshToken(String uuid) {
        return stringRedisTemplate.opsForValue().get(uuid);
    }

    //uuid로 refreshToken가 있는 지 확인
    public boolean hasKey(String uuid) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(uuid));
    }
}