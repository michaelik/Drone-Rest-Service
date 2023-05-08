package com.droneservice.controller.auth;

import com.droneservice.payload.request.SignInRequest;
import com.droneservice.payload.request.SignUpRequest;
import com.droneservice.payload.response.SignInResponse;
import com.droneservice.service.AuthenticationService;
import com.droneservice.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JWTUtil jwtUtil;

    @PostMapping(path = "/register", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest request) {
        authenticationService.signUp(request);
        String jwtToken = jwtUtil.issueToken(request.getEmail(), "ROLE_USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @PostMapping(path = "/login", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<?> login(@RequestBody SignInRequest request) {
        SignInResponse response = authenticationService.signIn(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getToken())
                .body(response);
    }
}
