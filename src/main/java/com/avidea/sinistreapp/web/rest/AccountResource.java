package com.avidea.sinistreapp.web.rest;

import com.avidea.sinistreapp.domain.User;
import com.avidea.sinistreapp.repositories.UserRepository;
import com.avidea.sinistreapp.security.UserDAO;
import com.avidea.sinistreapp.web.rest.exceptions.AuthenticationFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:4200")
public class AccountResource {

    private static final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final DaoAuthenticationProvider authenticationManager;

    private final UserRepository userRepository;

    public AccountResource(DaoAuthenticationProvider authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticateUser(@Valid @RequestBody UserDAO userDAO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDAO.getLogin(), userDAO.getPassword()));
        } catch (AuthenticationException e) {
            throw new AuthenticationFailureException("login ou mot de passe erroné");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByLogin(userDAO.getLogin()).get();
        return ResponseEntity.ok().body(user);
    }
}
