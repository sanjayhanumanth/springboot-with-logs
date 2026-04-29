package com.demo.config;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(User.Role.ROLE_ADMIN)
                        .build();
                userRepository.save(admin);
                log.info("Admin user created — username: admin / password: admin123");
            }

            if (!userRepository.existsByUsername("user")) {
                User user = User.builder()
                        .username("user")
                        .email("user@example.com")
                        .password(passwordEncoder.encode("user123"))
                        .role(User.Role.ROLE_USER)
                        .build();
                userRepository.save(user);
                log.info("Demo user created — username: user / password: user123");
            }
        };
    }
}
