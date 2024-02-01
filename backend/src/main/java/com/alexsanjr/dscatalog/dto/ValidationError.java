package com.alexsanjr.dscatalog.dto;

import java.time.Instant;
import java.util.List;

public record ValidationError(Instant timestamp, Integer status, String error, String path, List<FieldMessage> errors) {
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
