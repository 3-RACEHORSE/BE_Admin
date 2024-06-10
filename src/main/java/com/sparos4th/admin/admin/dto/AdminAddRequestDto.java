package com.sparos4th.admin.admin.dto;

import com.sparos4th.admin.common.AdminGrant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminAddRequestDto {

	private String email;
	private String password;
	private String name;
	private AdminGrant grant;

}
