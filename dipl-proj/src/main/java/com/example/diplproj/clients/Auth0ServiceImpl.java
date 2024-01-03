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

    public void createUser(String email, char[] password, List<String> roleIds) {
        SignUpRequest request = authAPI.signUp(email, password, connection);

        try {
            request.execute();
        } catch (Auth0Exception e) {
            throw new AuthException();
        }

        try {
            assignRoleToUser(getAuth0UserId(email), roleIds);
        } catch (Auth0Exception e) {
            throw new AuthException();
        }
    }

    public void deleteUser(String email) {
        managementAPI.users().delete(getAuth0UserId(email));
    }

    public void updateUserEmail(String emailOld, String emailNew) {
        Request<List<User>> listRequest = managementAPI.users().listByEmail(emailOld, new FieldsFilter());
        User user;

        try {
            user = listRequest.execute().getBody().get(0);
        } catch (Auth0Exception e) {
            throw new AuthException();
        }

        user.setEmail(emailNew);
        managementAPI.users().update(user.getId(), user);
    }

    private void assignRoleToUser(String userId, List<String> roleIds) throws Auth0Exception {
        // Assign the default role to the user
        managementAPI.users().addRoles(userId, roleIds);
    }

    private String getAuth0UserId(String email) {
        Request<List<User>> listRequest = managementAPI.users().listByEmail(email, new FieldsFilter());

        try {
            return listRequest.execute().getBody().get(0).getId();
        } catch (Auth0Exception e) {
            throw new AuthException();
        }
    }
}
