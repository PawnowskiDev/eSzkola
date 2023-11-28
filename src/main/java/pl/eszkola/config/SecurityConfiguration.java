package pl.eszkola.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/**").hasRole("TEACHER")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .requestMatchers("/parent/**").hasRole("PARENT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userAdmin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();

        UserDetails userTeacher = User.withUsername("teacher")
                .password(passwordEncoder().encode("teacher"))
                .roles("TEACHER").build();

        UserDetails userStudent = User.withUsername("student")
                .password(passwordEncoder().encode("student"))
                .roles("STUDENT").build();

        UserDetails userParent = User.withUsername("parent")
                .password(passwordEncoder().encode("parent"))
                .roles("PARENT").build();

        return new InMemoryUserDetailsManager(userAdmin, userTeacher, userStudent, userParent);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
