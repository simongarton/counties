package com.simongarton.register.controllers;

import com.simongarton.register.services.dto.UserDto;
import com.simongarton.register.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers(@RequestParam(value = "surname", required = false) String surname) {
        if (surname == null) {
            return userService.getAll();
        } else {
            return userService.findUsers(surname);
        }
    }

    @GetMapping("/users/{id}")
    private UserDto getUser(@PathVariable("id") long id) {
        return userService.get(id).get();
    }

    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @PostMapping("/users")
    private long saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }
}
