package com.alexsanjr.dscatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record newPasswordDTO(@NotBlank(message = "Required field") String token,
                             @NotBlank(message = "Required field")
                             @Size(min = 8, message = "Must be 8 characters") String password) {
}
