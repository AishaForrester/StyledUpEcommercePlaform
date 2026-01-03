package com.ecommerce.styledup;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.repository.UserRepository;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/", "/home", "/signup", "/products", "/css/**", "/js/**","/uploads/**", "/about","/contacts").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")                 
                .loginProcessingUrl("/login")        // URL Spring Security uses to submit credentials
                .successHandler(new CustomAuthenticationSuccessHandler())        // redirect after successful login
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .rememberMe(remember -> remember
                .key("styledup-remember-me-key")
                .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days, allows users to stay looged in for a week. Testing purposes
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }

   @Bean
public UserDetailsService userDetailsService(UserRepository userRepo) {
    return username -> {
        try {
            User user = userRepo.findByUsername(username); 
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole()))
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found", e);
        }
    };
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
}



}
