package com.sparos4th.admin.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminGrant {

	ALL,
	AUCTION_POST_READ,
	AUCTION_POST_WRITE,
	AUCTION_POST_DELETE,
	FAMOUS_PERSON_REGISTRATION
}
