package cz.slaw.hm.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(indexes = {
        @Index(name = "idx_user_entity_login", unique = true, columnList = "login")
})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    @Getter
    @Setter
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(max = 255)
    @Column(length = 255)
    @Getter
    @Setter
    private String password;

    @Version
    @Getter
    @Setter
    private Long version;

    public enum Role {
        ADMIN, USER;
    }

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Role role;

}
