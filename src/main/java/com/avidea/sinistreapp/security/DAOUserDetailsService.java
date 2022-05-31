package com.avidea.sinistreapp.security;

import com.avidea.sinistreapp.domain.User;
import com.avidea.sinistreapp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DAOUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DAOUserDetailsService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> maybeUser = userRepository.findByLogin(username);
        if (!maybeUser.isPresent())
            throw new UsernameNotFoundException(username);
        final User user = maybeUser.get();
        return org.springframework.security.core.userdetails.User.withUsername(user.getLogin())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
