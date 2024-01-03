package com.example.diplproj.clients;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.net.SignUpRequest;
import com.example.diplproj.exceptions.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class Auth0ServiceImpl implements Auth0Service {
    private final AuthAPI authAPI;
    @Value("${AUTH0_CONNECTION_DB}")
    private String connection;

    public void createUser(String email, char[] password) {
        SignUpRequest request = authAPI.signUp(email, password, connection);

        try {
            request.execute();
        } catch (Auth0Exception e) {
            throw new AuthException();
        }
    }
}
