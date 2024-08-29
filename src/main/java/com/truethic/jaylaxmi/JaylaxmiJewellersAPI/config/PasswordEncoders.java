package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class PasswordEncoders1 {
    public BCryptPasswordEncoder passwordEncoderNew() {
        return new BCryptPasswordEncoder();
    }
}
