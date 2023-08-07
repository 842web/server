package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter

public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),


    /**
     * 400 : Request 오류, Response 오류
     */
    // Common
    REQUEST_ERROR(false, HttpStatus.BAD_REQUEST.value(), "입력값을 확인해주세요."),
    WRONG_TYPE(false, HttpStatus.BAD_REQUEST.value(), "잘못된 형식입니다."),
    EMPTY_JWT(false, HttpStatus.UNAUTHORIZED.value(), "JWT IS EMPTY"),
    INVALID_JWT(false, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,HttpStatus.FORBIDDEN.value(),"권한이 없는 유저의 접근입니다."),
    EXPIRED_JWT(false, HttpStatus.UNAUTHORIZED.value(),"만료된 JWT입니다."),
    UNSUPPORTED_JWT(false, HttpStatus.UNAUTHORIZED.value(),"유효하지 않는 JWT 타입입니다."),
    ILLEGAL_JWT(false, HttpStatus.UNAUTHORIZED.value(),"JWT claims이 비어있습니다."),
    SIGNATURE_FAILE_JWT(false, HttpStatus.UNAUTHORIZED.value(),"JWT 서명 인증에 실패했습니다."),
    WRONG_JWT(false, HttpStatus.UNAUTHORIZED.value(),"JWT 관련 오류입니다."),
    RESPONSE_ERROR(false, HttpStatus.NOT_FOUND.value(), "값을 불러오는데 실패하였습니다."),

    EMPTY_REFRESH_TOKEN(false, HttpStatus.UNAUTHORIZED.value(),"Refresh Token이 입력되지 않았습니다."),
    INVALID_REFRESH_TOKEN(false, HttpStatus.UNAUTHORIZED.value(),"사용자의 Refresh Token과 일치하지 않습니다."),


    INVALID_USER_NO(false, HttpStatus.BAD_REQUEST.value(), "잘못된 형식의 유저 번호입니다."),
    INVALID_IDX(false, HttpStatus.BAD_REQUEST.value(), "잘못된 형식의 인덱스입니다."),
    INVALID_PAGE_NO(false, HttpStatus.BAD_REQUEST.value(), "잘못된 페이지 번호입니다."),


    /**
     * 50 : Database, Server 오류
     */
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버와의 연결에 실패하였습니다."),



    /*
    * 로그인, 회원 가입 오류
    * */

    IS_NOT_JOIN_US(false, HttpStatus.BAD_REQUEST.value(), "회원가입이 되어 있지 않은 유저 입니다. 회원가입 페이지로 이동해주세요");



    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}