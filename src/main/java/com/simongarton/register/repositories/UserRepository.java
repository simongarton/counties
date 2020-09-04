package com.simongarton.register.repositories;

import com.simongarton.register.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findBySurname(String surname);
}