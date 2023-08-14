package com.konkuk_hackathon.ohgamja.Common.Exception;

import com.konkuk_hackathon.ohgamja.Common.Response.Status.ResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ResponseStatus exceptionStatus;
    public BaseException(ResponseStatus exceptionStatus, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionStatus = exceptionStatus;
    }
}
