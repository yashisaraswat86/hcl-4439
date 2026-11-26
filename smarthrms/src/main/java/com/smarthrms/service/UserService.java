package com.smarthrms.service;

import com.smarthrms.dto.UserRegistrationRequest;
import com.smarthrms.dto.UserResponse;
import com.smarthrms.entity.User;
import com.smarthrms.enums.Role;
import com.smarthrms.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse registerUser(UserRegistrationRequest request) {

        // Check whether email already exists
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already registered"
            );
        }

        // Create user
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());

        // Hash password before saving
        user.setPasswordHash(
                passwordEncoder.encode(request.password())
        );

        user.setRole(Role.CANDIDATE);

        // Save user
        User savedUser = userRepository.save(user);

        // Return safe response
        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}
