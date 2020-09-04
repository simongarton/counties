package com.simongarton.register.services.dto;

import com.simongarton.register.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    private long id;
    @NotNull(message = "Surname cannot be null")
    private String surname;
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Email cannot be null")
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.surname = user.getSurname();
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
    }
}
