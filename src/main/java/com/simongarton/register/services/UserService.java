package com.simongarton.register.services;

import com.simongarton.register.services.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findUsers(String surname);

    List<UserDto> getAll();

    void delete(long id);

    Optional<UserDto> get(long id);

    long save(UserDto user);
}
