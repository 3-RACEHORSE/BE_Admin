package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.domain.Admin;
import com.sparos4th.admin.admin.dto.AdminAddRequestDto;
import com.sparos4th.admin.admin.dto.AdminLoginRequestDto;
import com.sparos4th.admin.admin.dto.TokenResponseDto;
import com.sparos4th.admin.admin.infrastructure.AdminRepository;
import com.sparos4th.admin.common.exception.CustomException;
import com.sparos4th.admin.common.exception.ResponseStatus;
import com.sparos4th.admin.common.security.JwtTokenProvider;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService{

	private final AdminRepository adminRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public TokenResponseDto login(AdminLoginRequestDto adminLoginRequestDto) {
		return null;
	}

	@Override
	@Transactional
	public void addAdmin(AdminAddRequestDto adminAddRequestDto, String loginUuid) {
		// 이메일 중복 확인
		adminRepository.findByEmail(adminAddRequestDto.getEmail()).ifPresent(m -> {
				throw new CustomException(ResponseStatus.DUPLICATE_EMAIL);
			});

		String uuid = "admin" + UUID.randomUUID();

		// 비밀번호 암호화
		String newPassword = hashPassword(adminAddRequestDto.getPassword());

		Admin admin = Admin.builder()
			.uuid(uuid)
			.email(adminAddRequestDto.getEmail())
			.password(newPassword)
			.name(adminAddRequestDto.getName())
			.grant(adminAddRequestDto.getGrant())
			.build();

		adminRepository.save(admin);
	}

	public String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public void changePassword(AdminChangePasswordRequestDto adminChangePasswordRequestDto) {

	}

	@Override
	public void changeGrant(AdminChangeGrantRequestDto adminChangeGrantRequestDto) {

	}

	@Override
	public void deleteAdmin(AdminDeleteRequestDto adminDeleteRequestDto) {

	}
}
