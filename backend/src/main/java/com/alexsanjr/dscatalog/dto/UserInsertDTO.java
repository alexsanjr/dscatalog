package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserInsertDTO(Long id, String firstName, String lastName, String email, String password, Set<RoleDTO> roles)  {
    public UserInsertDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toSet()));
    }
}
