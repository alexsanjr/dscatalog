package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.stream.Collectors;

public record UserInsertDTO(Long id, @NotBlank(message = "Required field") String firstName, String lastName,
                            @Email(message = "Please enter with valid email") String email, String password, Set<RoleDTO> roles)  {
    public UserInsertDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toSet()));
    }
}
