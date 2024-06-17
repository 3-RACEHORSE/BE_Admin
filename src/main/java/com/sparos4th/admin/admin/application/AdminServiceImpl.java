package com.sparos4th.admin.admin.application;

import com.sparos4th.admin.admin.domain.Admin;
import com.sparos4th.admin.admin.dto.AdminAddRequestDto;
import com.sparos4th.admin.admin.dto.AdminLoginRequestDto;
import com.sparos4th.admin.admin.dto.TokenResponseDto;
import com.sparos4th.admin.admin.infrastructure.AdminRepository;
import com.sparos4th.admin.common.AdminGrant;
import com.sparos4th.admin.common.exception.CustomException;
import com.sparos4th.admin.common.exception.ResponseStatus;
import com.sparos4th.admin.common.security.JwtTokenProvider;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService{

	private final AdminRepository adminRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public TokenResponseDto login(AdminLoginRequestDto adminLoginRequestDto) {
		// 이메일(ID)로 가입되어있는지 확인
		Admin admin = adminRepository.findByEmail(adminLoginRequestDto.getEmail())
			.orElseThrow(() -> new CustomException(ResponseStatus.FAILED_TO_LOGIN));

		// 해시 암호화된 DB의 비밀번호 저장값과 로그인 정보에 담긴 비밀번호 비교
		if (bCryptPasswordEncoder.matches(adminLoginRequestDto.getPassword(), admin.getPassword())) {
			String token = createToken(admin);
			return TokenResponseDto.builder()
				.accessToken(token)
				.build();

		} else throw new CustomException(ResponseStatus.FAILED_TO_LOGIN);
	}

	@Override
	@Transactional
	public void addAdmin(AdminAddRequestDto adminAddRequestDto, String accessToken) {
		// 조회 및 검증
		Admin checkAdmin = adminRepository.findByUuid(jwtTokenProvider.getUuid(accessToken))
			.orElseThrow(() -> new CustomException(ResponseStatus.UNAUTHORIZED_USER));
		AdminGrant role = checkAdmin.getGrant();
		if(role != AdminGrant.ALL) {
			throw new CustomException(ResponseStatus.UNAUTHORIZED_USER);
		}

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

	//	토큰 생성
	private String createToken(Admin admin) {
		UserDetails userDetails = User.withUsername(admin.getUuid())
			.roles(String.valueOf(admin.getGrant())).build();
		return jwtTokenProvider.generateToken(userDetails);
	}

//	@Override
//	public void changePassword(AdminChangePasswordRequestDto adminChangePasswordRequestDto) {
//
//	}
//
//	@Override
//	public void changeGrant(AdminChangeGrantRequestDto adminChangeGrantRequestDto) {
//
//	}
//
//	@Override
//	public void deleteAdmin(AdminDeleteRequestDto adminDeleteRequestDto) {
//
//	}
}
