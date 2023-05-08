package com.droneservice.service;

import com.droneservice.payload.request.SignInRequest;
import com.droneservice.payload.request.SignUpRequest;
import com.droneservice.payload.response.SignInResponse;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {
    void signUp(SignUpRequest request);

    SignInResponse signIn(SignInRequest request);
}
