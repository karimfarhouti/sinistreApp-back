package com.avidea.sinistreapp;

import com.avidea.sinistreapp.domain.User;
import com.avidea.sinistreapp.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SinistreAppApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SinistreAppApplication.class, args);
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        PasswordEncoder encoder = ctx.getBean(PasswordEncoder.class);

        User user = new User();
        user.setLogin("karim");
        String encodedPassword = encoder.encode("karim123");
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

}
