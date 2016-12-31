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

    @RequestMapping(value = "/user/", method = RequestMethod.PUT)
    public UserEntity update(@RequestBody UserEntity inUser) {
        UserEntity user = userService.update(inUser);
        return user;
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public void create(@RequestBody UserEntity inUser) {
        userService.create(inUser);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserEntity findById(@PathVariable("id") Long id) {
        UserEntity user = userService.findById(id);
        return user;
    }

}
