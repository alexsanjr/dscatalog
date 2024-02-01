package com.alexsanjr.dscatalog.services.validation;

import com.alexsanjr.dscatalog.dto.FieldMessage;
import com.alexsanjr.dscatalog.dto.UserInsertDTO;
import com.alexsanjr.dscatalog.entities.User;
import com.alexsanjr.dscatalog.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;
    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.email());
        if (user != null) {
            list.add(new FieldMessage("email", "Email exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}