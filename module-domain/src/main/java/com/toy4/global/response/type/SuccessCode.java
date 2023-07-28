package com.toy4.global.response.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    SUCCESS("성공했습니다."),

    AVAILABLE_EMAIL("사용 가능한 이메일입니다."),
    AVAILABLE_PASSWORD("사용 가능한 패스워드입니다."),

    COMPLETE_CHANGE_PASSWORD("비밀번호가 변경되었습니다."),
    COMPLETE_EMAIL_TRANSMISSION("이메일 전송이 완료되었습니다."),
    ;

    private final String message;
}
