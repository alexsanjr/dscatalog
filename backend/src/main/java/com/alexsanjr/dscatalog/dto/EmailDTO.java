package com.alexsanjr.dscatalog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(@NotBlank(message = "Required field") @Email(message = "Invalid email") String email) {
}
