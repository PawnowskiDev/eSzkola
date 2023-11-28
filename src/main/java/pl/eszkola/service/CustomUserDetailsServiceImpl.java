package pl.eszkola.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.UserType;
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

        MyUser myUser = userRepository.findByEmail(pesel);

        if (myUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + pesel);
        }
        return new org.springframework.security.core.userdetails.User(
                myUser.getPesel(),
                myUser.getPassword(),
                getAuthorities(myUser.getUserType())
        );
        }

    private Collection<? extends GrantedAuthority> getAuthorities(UserType role) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}


