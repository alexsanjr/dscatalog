package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.User;
import com.alexsanjr.dscatalog.services.validation.UserUpdateValid;

import java.util.Set;
import java.util.stream.Collectors;

@UserUpdateValid
public record UserDTO(Long id, String firstName, String lastName, String email, Set<RoleDTO> roles) {

    public UserDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toSet()));
    }
}
