package com.springsecurity.demo.controllers;

import com.springsecurity.demo.exceptions.NotFoundException;
import com.springsecurity.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springsecurity.demo.models.User;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/v1/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userid}")
    public ResponseEntity<User> getUser(@PathVariable Long userid)
            throws NotFoundException
    {
        return new ResponseEntity<>(userService.fetchUser(userid), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userService.fetchAllUsers(), HttpStatus.OK);
    }


}
