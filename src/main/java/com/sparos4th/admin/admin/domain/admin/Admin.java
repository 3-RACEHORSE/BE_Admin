package com.sparos4th.admin.admin.domain.admin;

import com.sparos4th.admin.common.AdminGrant;
import com.sparos4th.admin.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private Long id;
	@Column(name = "admin_uuid", nullable = false, unique = true)
	private String uuid;
	@Column(name = "admin_name", nullable = false, length = 20)
	private String name;
	@Column(name = "admin_email", nullable = false, length = 30, unique = true)
	private String email;
	@Column(name = "admin_password", nullable = false)
	private String password;
	@Column(name = "admin_grant", nullable = false)
	@Enumerated(EnumType.STRING)
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
