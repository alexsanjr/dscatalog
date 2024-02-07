package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.User;
import com.alexsanjr.dscatalog.services.validation.UserInsertValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.stream.Collectors;

@UserInsertValid
public record UserInsertDTO(Long id, @NotBlank(message = "Required field") String firstName, String lastName,
                            @Email(message = "Please enter with valid email") String email,
                            @NotBlank(message = "Required field")
                            @Size(min = 8, message = "Must have at least 8 characters")
                            String password,
                            Set<RoleDTO> roles)  {
    public UserInsertDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toSet()));
    }
}
