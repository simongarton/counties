package com.simongarton.register.services;

import com.simongarton.register.exceptions.NotFoundException;
import com.simongarton.register.model.User;
import com.simongarton.register.repositories.UserRepository;
import com.simongarton.register.services.dto.UserDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findUsers(String surname) {
        List<UserDto> users = new ArrayList<>();
        userRepository.findBySurname(surname).forEach(user -> users.add(new UserDto(user)));
        return users;
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(new UserDto(user)));
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
    @Transactional
    public UserDto save(UserDto userDto) {
        Optional<User> user = getUser(userDto.getId());
        User userRecord = user.orElseGet(User::new);
        userRecord.setFirstName(userDto.getFirstName());
        userRecord.setSurname(userDto.getSurname());
        userRecord.setEmail(userDto.getEmail());
        userRepository.save(userRecord);
        return new UserDto(userRecord);
    }

    @Override
    @Transactional
    public void delete(long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException erdae) {
            throw new NotFoundException("User " + id + " not found.");
        }
    }
}
