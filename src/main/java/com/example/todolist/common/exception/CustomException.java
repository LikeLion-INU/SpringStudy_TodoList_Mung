package com.example.todolist.common.exception;

import com.example.todolist.common.response.ApiResponse;
import com.example.todolist.common.response.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    //    private String key;
    private String msg;
    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    public CustomException(ErrorCode errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }
//    public CustomException(String key, ErrorCode errorCode) {
//        super(errorCode.getMsg());
//        this.key = key;
//        this.errorCode = errorCode;
//    }

    public ApiResponse<?> body(){
//        Valid valid = new Valid(key, errorCode.getMsg());
        return ApiResponse.FAILURE(errorCode.getCode(), "CustomException : " + errorCode.getMsg());
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}
