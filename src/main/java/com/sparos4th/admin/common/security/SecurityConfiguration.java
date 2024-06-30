package com.sparos4th.admin.common.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			var cors = new org.springframework.web.cors.CorsConfiguration();
			cors.setAllowedOriginPatterns(List.of("https://racehorseteam.store", "http://localhost:3000", "https://fe-meetplus.vercel.app", "https://fe-admin-xi.vercel.app/"));
			cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));
			cors.setAllowedHeaders(List.of("*"));
			cors.setAllowCredentials(true);
			return cors;
		};
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(CsrfConfigurer::disable)
			.authorizeHttpRequests(
				authorizeHttpRequests -> authorizeHttpRequests
					.requestMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
					// 허용 범위
					.requestMatchers(
						antMatcher("/api/v1/admin/auth/**"),
						antMatcher("/api/v1/admin/health-check"),
						antMatcher("/swagger-ui/**"),
						antMatcher("/swagger-resources/**"),
						antMatcher("/v3/api-docs/**"),
						antMatcher("/error"))
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.sessionManagement(
				sessionManagement -> sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authenticationProvider(
				authenticationProvider) //등록할때 사용하는 키는 authenticationProvider를 사용
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //내가 만든 필터 추가

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
