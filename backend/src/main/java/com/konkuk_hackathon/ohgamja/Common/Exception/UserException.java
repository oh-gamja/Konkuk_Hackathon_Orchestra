package com.konkuk_hackathon.ohgamja.Common.Exception;

import com.konkuk_hackathon.ohgamja.Common.Response.Status.ResponseStatus;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private final ResponseStatus exceptionStatus;

    public UserException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
