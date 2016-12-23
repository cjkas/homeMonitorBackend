package cz.slaw.hm.dto;

import lombok.Getter;
import lombok.Setter;
import cz.slaw.hm.domain.UserEntity;

import java.io.Serializable;

/**
 * Created by Sławek on 2016-12-10.
 */
public class LoginResponse implements Serializable {
    @Getter @Setter private UserEntity user;

    @Getter @Setter private String token;
}
