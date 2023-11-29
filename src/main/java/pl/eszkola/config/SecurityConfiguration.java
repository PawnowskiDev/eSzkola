package pl.eszkola.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import pl.eszkola.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {



    @Bean
    public SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurer(UserService userService) {
        return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http
                        .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/*").permitAll()
                                .requestMatchers("/templates/admin/**").hasRole("ADMIN")
                                .requestMatchers("/templates/teacher/**").hasRole("TEACHER")
                                .requestMatchers("/templates/student/**").hasRole("STUDENT")
                                .requestMatchers("/templates/parent/**").hasRole("PARENT")
                                .requestMatchers("/templates/style/**").permitAll()
                                .requestMatchers("/templates/").permitAll()
                                .anyRequest().authenticated()
                        )
                        .formLogin(form -> form
                                .loginPage("/templates/auth/login")
                                .permitAll()
                        )
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                        )
                        .exceptionHandling(exceptions -> exceptions
                                .accessDeniedPage("/access-denied")
                        );
            }
        };
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
