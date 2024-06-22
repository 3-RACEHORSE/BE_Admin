package com.sparos4th.admin.common.security;

import com.sparos4th.admin.common.exception.CustomException;
import com.sparos4th.admin.common.exception.ResponseStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

	private final Environment env;

	@Value("${JWT.SECRET_KEY}")
	private String SECRETKEY;

	@Value("${JWT.ACCESS_TOKEN_EXPIRATION_TIME}")
	private long ACCESS_TOKEN_EXPIRATION_TIME;

	@Value("${JWT.REFRESH_TOKEN_EXPIRATION_TIME}")
	private long REFRESH_TOKEN_EXPIRATION_TIME;

	public String getUuid(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("JWT.SECRET_KEY"));
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(Map.of(), userDetails);
	}

	public String generateRefreshToken(UserDetails userDetails) {
		return generateRefreshToken(Map.of(), userDetails);
	}

	public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		String role = String.valueOf(userDetails.getAuthorities().stream().findFirst().orElseThrow(() -> new CustomException(
			ResponseStatus.UNAUTHORIZED_USER)));

		log.info("generateToken {}", userDetails);
		Map<String, Object> modifiableExtractClaims = new HashMap<>(extractClaims);
		modifiableExtractClaims.put("TokenType", "access");

		return Jwts.builder()
			.setClaims(modifiableExtractClaims) //정보저장
			.claim("role", role)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis())) //토근 발행 시간
			.setExpiration(
				new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME)) //토큰 만료 시간
			.signWith(getSigningKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		String role = String.valueOf(userDetails.getAuthorities().stream().findFirst().orElseThrow(() -> new CustomException(
			ResponseStatus.UNAUTHORIZED_USER)));
		Map<String, Object> modifiableExtractClaims = new HashMap<>(extractClaims);
		modifiableExtractClaims.put("TokenType", "refresh");

		return Jwts.builder()
			.setClaims(modifiableExtractClaims) //정보저장
			.claim("role", role)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis())) //토근 발행 시간
			.setExpiration(
				new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME)) //토큰 만료 시간
			.signWith(getSigningKey(), SignatureAlgorithm.HS256)
			.compact();

	}

	public void validateJwtToken(String token) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token);
			String tokenType = claimsJws.getBody().get("TokenType", String.class);
			log.info("tokenType: {}", tokenType);
			if ("refresh".equals(tokenType)) {
				throw new CustomException(ResponseStatus.JWT_FAIL_WITH_REFRESH);
			}
		} catch (SignatureException e) {
			throw new CustomException(ResponseStatus.INVALID_SIGNATURE_TOKEN);
		} catch (MalformedJwtException e) {
			throw new CustomException(ResponseStatus.DAMAGED_TOKEN);
		} catch (UnsupportedJwtException e) {
			throw new CustomException(ResponseStatus.UNSUPPORTED_TOKEN);
		} catch (ExpiredJwtException e) {
			throw new CustomException(ResponseStatus.EXPIRED_TOKEN);
		} catch (IllegalArgumentException e) {
			throw new CustomException(ResponseStatus.INVALID_TOKEN);
		} catch (Exception e) {
			throw new CustomException(ResponseStatus.VERIFICATION_FAILED);
		}
	}
}
