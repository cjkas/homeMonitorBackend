package cz.slaw.hm.controller;

import cz.slaw.hm.domain.UserEntity;
import cz.slaw.hm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    private static final Logger log = LoggerFactory.getLogger(SecurityController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public UserEntity getUserAccount(Principal principal) {
        UserEntity user = principal != null ? userService.findByLogin(principal.getName()) : null;
        return user;
    }

}