package cz.slaw.hm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Sławek on 2016-12-10.
 */
public class LoginRequest implements Serializable {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

}
