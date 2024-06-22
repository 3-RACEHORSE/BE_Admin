package com.sparos4th.admin.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparos4th.admin.common.CommonResponse;
import com.sparos4th.admin.common.exception.CustomException;
import com.sparos4th.admin.common.exception.ResponseStatus;
import com.sparos4th.admin.common.redis.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserDetailsService userDetailsService;
	private final RedisUtils redisUtils;


	@Override
	protected void doFilterInternal(
		@NonNull
		HttpServletRequest request,
		@NonNull
		HttpServletResponse response,
		@NonNull
		FilterChain filterChain
	) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userUuid;

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(7);
		jwtTokenProvider.validateJwtToken(jwt);

		filterChain.doFilter(request, response);

	}
}
