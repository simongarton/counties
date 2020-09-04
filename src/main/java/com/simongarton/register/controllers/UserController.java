package com.simongarton.register.controllers;

import com.simongarton.register.services.UserService;
import com.simongarton.register.services.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers(@RequestParam(value = "surname", required = false) String surname) {
        if (surname == null) {
            return userService.getUsers();
        } else {
            return userService.findUsers(surname);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private UserDto getUser(@PathVariable("id") long id) {
        return userService.get(id).orElse(null);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    private void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private UserDto saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }
}
