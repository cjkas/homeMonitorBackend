package cz.slaw.hm.dto;

import lombok.Getter;

import java.lang.*;

/**
 * Created by Sławek on 2016-12-07.
 */
public class ResponseError {

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private Error error;

    public ResponseError(int code, String message, Error error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }

}