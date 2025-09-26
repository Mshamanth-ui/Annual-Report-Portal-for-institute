package com.azbugbusters.institute.config;

import com.azbugbusters.institute.model.Role;
import com.azbugbusters.institute.model.User;
import com.azbugbusters.institute.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                User u = new User();
                u.setUsername("admin");
                u.setPasswordHash(encoder.encode("password"));
                u.setRole(Role.ADMIN);
                repo.save(u);
            }
            if (repo.findByUsername("faculty").isEmpty()) {
                User u = new User();
                u.setUsername("faculty");
                u.setPasswordHash(encoder.encode("password"));
                u.setRole(Role.FACULTY);
                repo.save(u);
            }
            if (repo.findByUsername("student").isEmpty()) {
                User u = new User();
                u.setUsername("student");
                u.setPasswordHash(encoder.encode("password"));
                u.setRole(Role.STUDENT);
                repo.save(u);
            }
        };
    }
}


