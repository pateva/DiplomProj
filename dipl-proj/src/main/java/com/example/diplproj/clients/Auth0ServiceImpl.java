package com.example.diplproj.clients;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.FieldsFilter;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import com.auth0.net.SignUpRequest;
import com.example.diplproj.exceptions.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Auth0ServiceImpl implements Auth0Service {
    private final AuthAPI authAPI;
    private final ManagementAPI managementAPI;
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

    public void deleteUser(String email) {
        Request<List<User>> listRequest = managementAPI.users().listByEmail(email, new FieldsFilter());
        String id;

        try {
            id = listRequest.execute().getBody().get(0).getId();
        } catch (Auth0Exception e) {
            throw new AuthException();
        }

        managementAPI.users().delete(id);
    }
}
