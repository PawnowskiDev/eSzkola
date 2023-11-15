package pl.eszkola.service;

import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordService implements PasswordService {

    private final BCryptPasswordService passwordEncoder;

    public BCryptPasswordService(BCryptPasswordService passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword (String rawPassword) {
        return passwordEncoder.encodePassword(rawPassword);
    }
}
