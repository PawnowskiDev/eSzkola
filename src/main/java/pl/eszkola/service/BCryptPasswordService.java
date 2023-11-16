package pl.eszkola.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordService implements PasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptPasswordService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword (String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
