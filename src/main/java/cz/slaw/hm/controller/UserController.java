package cz.slaw.hm.controller;

import cz.slaw.hm.domain.UserEntity;
import cz.slaw.hm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public List<UserEntity> listAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public UserEntity update(@PathVariable("id") Long id, @RequestBody UserEntity inUser) {
        UserEntity user = userService.update(id, inUser);
        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserEntity findById(@PathVariable("id") Long id) {
        UserEntity user = userService.findById(id);
        return user;
    }

}
