package com.bookish.Bookish.auth;

import com.bookish.Bookish.jwt.JwtService;
import com.bookish.Bookish.model.User;
import com.bookish.Bookish.model.enums.Role;
import com.bookish.Bookish.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepo userRepo;

    private final JwtService jwtService;
    public AuthResponse login(LoginRequest request) {
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(Role.CUSTOMER)
                .build();
        userRepo.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
