package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.jwt.JwtService;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user =  userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public User register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);

        return user;
    }
}
