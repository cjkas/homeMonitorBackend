package cz.slaw.hm.dto;

import lombok.Getter;

/**
 * Created by Sławek on 2016-12-07.
 */
public class Error {

    @Getter
    private String reason;

    @Getter
    private String message;

    public Error(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }
}