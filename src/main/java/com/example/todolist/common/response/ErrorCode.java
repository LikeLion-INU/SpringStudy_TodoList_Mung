package com.example.todolist.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /** Exception40x **/
    ACCESS_DENIAL(403, "권한이 없습니다."),
    USER_NOT_FOUND(404, "존재하지 않은 회원입니다."),
    EMAIL_NOT_FOUND(404, "이메일이 틀렸습니다"),
    PASSWORD_NOT_FOUND(404, "비밀번호가 틀렸습니다."),
    TODO_NOT_FOUND(404, "존재하지 않는 투두입니다."),

    EMAIL_EXIST(409, "이미 가입된 이메일입니다."),

    /** Exception500 **/
    SERVER_ERROR(500, "서버 에러입니다.");

    /** Success **/

    private final int code;
    private final String msg;
}