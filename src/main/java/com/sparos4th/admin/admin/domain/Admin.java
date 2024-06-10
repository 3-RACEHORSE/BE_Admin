package com.sparos4th.admin.admin.domain;

import com.sparos4th.admin.common.AdminGrant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private Long id;
	@Column(name = "admin_uuid", nullable = false)
	private String uuid;
	@Column(name = "admin_name", nullable = false, length = 20)
	private String name;
	@Column(name = "admin_email", nullable = false, length = 30)
	private String email;
	@Column(name = "admin_password", nullable = false, length = 50)
	private String password;
	@Column(name = "admin_grant", nullable = false)
	private AdminGrant grant;

	@Builder
	public Admin(Long id, String uuid, String name, String email, String password, AdminGrant grant) {
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.grant = grant;
	}
}
