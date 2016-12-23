package cz.slaw.hm.service.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.slaw.hm.domain.UserEntity;
import cz.slaw.hm.repository.UserRepository;
import cz.slaw.hm.service.UserService;

import java.util.List;

/**
 * Created by Sławek on 2016-12-03.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserEntity addUser(UserEntity entity) {
        log.info("addUser, {}", ReflectionToStringBuilder.toString(entity));
        entity.setRole(UserEntity.Role.ADMIN);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
        return entity;
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> all = userRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
        return all;
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    @Override
    public UserEntity update(Long id, UserEntity entity) {
        boolean exists = userRepository.exists(id);
        if (!exists) {
            return null;
        }
        entity.setId(id);
        userRepository.save(entity);
        return entity;
    }

    @Override
    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
