package com.example.demo.config.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum Code {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(HttpStatus.OK, true, 2000, "요청에 성공하였습니다."),


    /**
     * 400 : Request 오류, Response 오류
     */
    // Common
    REQUEST_ERROR(HttpStatus.BAD_REQUEST, false, 4000, "입력값을 확인해주세요."),
    WRONG_TYPE(HttpStatus.BAD_REQUEST, false, 4001, "잘못된 형식입니다."),
    EMPTY_JWT(HttpStatus.UNAUTHORIZED, false, 4002, "JWT를 입력해주세요."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, false, 4003, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(HttpStatus.UNAUTHORIZED, false, 4004,"권한이 없는 유저의 접근입니다."),
    RESPONSE_ERROR(HttpStatus.NOT_FOUND, false, 4005, "값을 불러오는데 실패하였습니다."),

    // INVALID
    INVALID_USER_NO(HttpStatus.BAD_REQUEST, false, 4006, "잘못된 형식의 유저 번호입니다."),
    INVALID_IDX(HttpStatus.BAD_REQUEST, false, 4007, "잘못된 형식의 인덱스입니다."),
    INVALID_PAGE_NO(HttpStatus.BAD_REQUEST, false, 4008, "잘못된 페이지 번호입니다."),

    // NOT FOUND
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, false,  4009, "포스트가 존재하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, false,  4010, "유저가 존재하지 않습니다."),
    POST_IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST, false,  4011, "포스트 이미지가 존재하지 않습니다."),

    /**
     * 50 : Database, Server 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 5000, "서버 내부에서 에러가 발생했습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 5001, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 5002, "서버와의 연결에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;

     Code(HttpStatus httpStatus, boolean isSuccess, int code, String message) {
        this.httpStatus = httpStatus;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
