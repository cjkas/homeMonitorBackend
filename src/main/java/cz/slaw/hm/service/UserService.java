package cz.slaw.hm.service;

import cz.slaw.hm.domain.UserEntity;

import java.util.List;

public interface UserService {

    void create(UserEntity dto);

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    UserEntity update(UserEntity user);

    UserEntity findByLogin(String name);

    void delete(Long id);
}
