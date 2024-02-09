package com.alexsanjr.dscatalog.services;

import com.alexsanjr.dscatalog.dto.EmailDTO;
import com.alexsanjr.dscatalog.entities.PasswordRecover;
import com.alexsanjr.dscatalog.entities.User;
import com.alexsanjr.dscatalog.repositories.PasswordRecoverRepository;
import com.alexsanjr.dscatalog.repositories.UserRepository;
import com.alexsanjr.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    @Value(value = "${email.password-recover.token.minutes}")
    private Long tokenMinute;
    @Value(value = "${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    @Transactional
    public void createRecoverToken(EmailDTO body) {

        User user = userRepository.findByEmail(body.email());

        if (user == null) {
            throw new ResourceNotFoundException("Email not found");
        }

        String token = UUID.randomUUID().toString();

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.email());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinute * 60));
        entity = passwordRecoverRepository.save(entity);

        String emailBody = "Click the link to set your new password\n\n"
                + recoverUri + token +". Validity of " + tokenMinute + " minutes.";
        emailService.sendEmail(entity.getEmail(), "Password recover", emailBody);
    }
}
