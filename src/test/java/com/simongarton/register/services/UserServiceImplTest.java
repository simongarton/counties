package com.simongarton.register.services;

import com.simongarton.register.CountiesPowerUserRegisterApplication;
import com.simongarton.register.services.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CountiesPowerUserRegisterApplication.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findUsers() {
        List<UserDto> userDtoList = userService.findUsers("Baggins");
        assertNotNull(userDtoList);
        assertEquals(2, userDtoList.size());
        assertEquals("Bilbo", userDtoList.get(0).getFirstName());
        assertEquals("Frodo", userDtoList.get(1).getFirstName());
    }

    @Test
    void getAll() {
        List<UserDto> userDtoList = userService.getUsers();
        assertNotNull(userDtoList);
        assertEquals(3, userDtoList.size());
        UserDto userDto = userDtoList.get(2);
        assertEquals("Sam", userDto.getFirstName());
    }

    @Test
    void get() {
        UserDto userDto = userService.get(1).orElse(null);
        assertNotNull(userDto);
        assertEquals(1, userDto.getId());
        assertEquals("Bilbo", userDto.getFirstName());
        assertEquals("Baggins", userDto.getSurname());
        assertEquals("bb@hobbiton.com", userDto.getEmail());
    }

    @Test
    void save_update() {
        UserDto userDto = userService.get(1).orElse(null);
        assertNotNull(userDto);
        assertEquals("bb@hobbiton.com", userDto.getEmail());
        userDto.setEmail("bilbo@gmail.com");
        UserDto savedUserDto = userService.save(userDto);
        assertNotNull(savedUserDto);
        assertEquals("bilbo@gmail.com", savedUserDto.getEmail());
    }

    @Test
    void save_insert() {
        int userCount = userService.getUsers().size();
        UserDto userDto = UserDto.builder()
                .firstName("Gandalf")
                .surname("The Grey")
                .email("g@middleearth.com")
                .build();
        UserDto savedUserDto = userService.save(userDto);
        assertNotNull(savedUserDto);
        assertEquals("g@middleearth.com", savedUserDto.getEmail());
        assertEquals(userCount + 1, userService.getUsers().size());
    }

    @Test
    void delete() {
        UserDto userDto = UserDto.builder()
                .firstName("Radagast")
                .surname("The Brown")
                .email("birdfriend@middleearth.com")
                .build();
        UserDto savedUserDto = userService.save(userDto);
        int userCount = userService.getUsers().size();
        userService.delete(savedUserDto.getId());;
        assertEquals(userCount - 1, userService.getUsers().size());
    }
}