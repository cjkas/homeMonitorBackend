package cz.slaw.hm.controller;

import cz.slaw.hm.service.UserService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import cz.slaw.hm.domain.UserEntity;

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

    @RequestMapping(value = "/security/register", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserEntity user, UriComponentsBuilder ucBuilder) {
        log.info("createUser, {}", ReflectionToStringBuilder.toString(user));
        if (userService.findByLogin(user.getLogin()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        UserEntity savedUser = userService.addUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


}