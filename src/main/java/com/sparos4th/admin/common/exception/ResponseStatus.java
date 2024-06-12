package com.sparos4th.admin.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
    /**
     * 200: 요청 성공
     **/
    SUCCESS(200, "요청에 성공했습니다."),

    /**
     * 토큰 에러
     */
    WRONG_JWT_TOKEN(400, "다시 로그인 해주세요"),
    USER_NOT_FOUND(201, "존재하지 않는 유저입니다"),
    /**
     * 400: 이미지 요청 오류
     */
    NO_EXIST_IMAGE(404, "존재하지 않는 이미지 입니다"),
    /**
     * 500: 기타 에러
     */
    INTERNAL_SERVER_ERROR(500, "Internal server error"),

    // Token, Code
    TOKEN_EXPIRED(401, "토큰이 만료되었습니다."),
    TOKEN_NOT_VALID(401, "토큰이 유효하지 않습니다."),
    TOKEN_NULL(401, "토큰이 존재하지 않습니다."),
    JWT_CREATE_FAILED(500, "토큰 생성에 실패했습니다."),
    JWT_VALID_FAILED(400, "토큰 검증에 실패했습니다."),
    EXPIRED_AUTH_CODE(400, "인증번호가 만료되었거나 존재하지 않는 멤버입니다."),
    WRONG_AUTH_CODE(400, "인증번호가 일치하지 않습니다."),
    LOGOUT_TOKEN(401, "로그아웃된 토큰입니다.");

    private final int code;
    private final String message;
}
