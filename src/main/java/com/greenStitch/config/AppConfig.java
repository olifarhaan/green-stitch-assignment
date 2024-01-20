package com.greenStitch.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails olifarhaan = User.builder().username("olifarhaan@gmail.com").password(passwordEncoder().encode("12345")).roles("ADMIN").build();
//        UserDetails rashmi = User.builder().username("rashmi@gmail.com").password(passwordEncoder().encode("12345")).roles("NORMAL").build();
//        return new InMemoryUserDetailsManager(olifarhaan, rashmi);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
