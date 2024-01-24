package com.alexsanjr.dscatalog.dto;

import com.alexsanjr.dscatalog.entities.Role;

public record RoleDTO(Long id, String authority) {
    public RoleDTO(Role role) {
        this(role.getId(), role.getAuthority());
    }
}
