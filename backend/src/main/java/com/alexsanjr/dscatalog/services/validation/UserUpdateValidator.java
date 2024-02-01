package com.alexsanjr.dscatalog.services.validation;

import com.alexsanjr.dscatalog.dto.FieldMessage;
import com.alexsanjr.dscatalog.dto.UserDTO;
import com.alexsanjr.dscatalog.entities.User;
import com.alexsanjr.dscatalog.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository repository;
    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.email());
        if (user != null && userId != user.getId()) {
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