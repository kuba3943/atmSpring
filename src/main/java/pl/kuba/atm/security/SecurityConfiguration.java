package pl.kuba.atm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {


        @Bean(name = "passwordEncoder")
    public PasswordEncoder noopPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
