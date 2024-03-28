package com.example.todolist.common.exception;

import com.example.todolist.common.response.ApiResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public ApiResponse<?> body(){
        return ApiResponse.ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception500 : ServerError");
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
