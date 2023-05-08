package com.droneservice.service.implementation;

import com.droneservice.enums.Gender;
import com.droneservice.exception.DuplicateResourceException;
import com.droneservice.jwt.JWTUtil;
import com.droneservice.model.User;
import com.droneservice.payload.request.SignInRequest;
import com.droneservice.payload.request.SignUpRequest;
import com.droneservice.payload.response.SignInResponse;
import com.droneservice.repository.AuthenticationRepository;
import com.droneservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    @Override
    public void signUp(SignUpRequest request) {
        boolean userEmailExist = authenticationRepository
                .findByEmail(request.getEmail())
                .isPresent();
        if (userEmailExist) throw new DuplicateResourceException("Email Already Taken");
        Gender gender = Gender.valueOf(request.getGender());

        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getAge(),
                gender);

        authenticationRepository.save(user);
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User principal = (User) authentication.getPrincipal();
        List<String> roles = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = jwtUtil.issueToken(principal.getUsername(), roles);
        return new SignInResponse(token, principal);
    }
}
