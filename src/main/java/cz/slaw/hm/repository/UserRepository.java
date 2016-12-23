package cz.slaw.hm.repository;

import cz.slaw.hm.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Sławek on 2016-12-04.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.login  = ?1")
    UserEntity findByLogin(String login);

}
