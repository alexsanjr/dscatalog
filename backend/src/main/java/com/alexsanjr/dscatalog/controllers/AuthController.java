package com.alexsanjr.dscatalog.controllers;

import com.alexsanjr.dscatalog.dto.EmailDTO;
import com.alexsanjr.dscatalog.dto.newPasswordDTO;
import com.alexsanjr.dscatalog.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO body) {
        service.createRecoverToken(body);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/new-password")
    public ResponseEntity<Void> savePassword(@Valid @RequestBody newPasswordDTO body) {
        service.saveNewPassword(body);
        return ResponseEntity.noContent().build();
    }

}
