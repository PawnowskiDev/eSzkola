package pl.eszkola.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.eszkola.model.User;
import pl.eszkola.model.UserRole;
import pl.eszkola.repository.UserRepository;
import java.util.Collection;
import java.util.Collections;


@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pesel) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(pesel);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + pesel);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getPesel(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );


        }

    private Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}


