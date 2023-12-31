package com.konkuk_hackathon.ohgamja.Common.Exception_handler;

import com.konkuk_hackathon.ohgamja.Common.Exception.UserException;
import com.konkuk_hackathon.ohgamja.Common.Response.BaseErrorResponse;
import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class UserExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public BaseErrorResponse handel_UserException(UserException e) {
        log.error("[handle_UserException]", e);
        return new BaseErrorResponse(e.getExceptionStatus(), e.getMessage());
    }
}
