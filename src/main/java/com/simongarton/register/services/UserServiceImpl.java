package com.simongarton.register.services;

import com.simongarton.register.exceptions.NotFoundException;
import com.simongarton.register.model.User;
import com.simongarton.register.repositories.UserRepository;
import com.simongarton.register.services.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> findUsers(String surname) {
        List<UserDto> users = new ArrayList<>();
        userRepository.findBySurname(surname).forEach(user -> {
            users.add(new UserDto(user));
        });
        return users;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            users.add(new UserDto(user));
        });
        return users;
    }

    @Override
    public Optional<UserDto> get(long id) {
        Optional<User> user = getUser(id);
        return user.map(UserDto::new);
    }

    private Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public long save(UserDto userDto) {
        Optional<User> user = getUser(userDto.getId());
        User userRecord = user.orElseGet(User::new);
        userRecord.setFirstName(userDto.getFirstName());
        userRecord.setSurname(userDto.getSurname());
        userRecord.setEmail(userDto.getEmail());
        userRepository.save(userRecord);
        return userRecord.getId();
    }

    @Override
    public void delete(long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException erdae) {
            throw new NotFoundException("User " + id + " not found.");
        }
    }
}
