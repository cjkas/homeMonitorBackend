package cz.slaw.hm.service;

import cz.slaw.hm.domain.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity addUser(UserEntity dto);

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    UserEntity update(Long id, UserEntity user);

    UserEntity findByLogin(String name);
}
