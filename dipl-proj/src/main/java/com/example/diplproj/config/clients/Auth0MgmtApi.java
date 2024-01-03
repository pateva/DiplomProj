package com.example.diplproj.config.clients;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.net.TokenRequest;
import com.example.diplproj.exceptions.AuthException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Auth0MgmtApi {
    @Value("${AUTH0_CLIENT_ID_MGMT}")
    private String clientId;

    @Value("${AUTH0_CLIENT_SECRET_MGMT}")
    private String clientSecret;

    @Value("${AUTH0_DOMAIN}")
    private String domain;

    @Value("${AUTH0_AUDIENCE_MGMT}")
    private String audienceMgmt;

    @Bean
    public AuthAPI createAuth0MgmtApi() {
        return AuthAPI.newBuilder(domain, clientId, clientSecret).build();
    }

    @Bean
    public ManagementAPI createManagmentApi() {
       TokenRequest tokenRequest = createAuth0MgmtApi().requestToken(audienceMgmt);

        try {
            String token = tokenRequest.execute().getBody().getAccessToken();
            return ManagementAPI.newBuilder(domain, token).build();
        } catch (Auth0Exception e) {
            throw new AuthException();
        }
    }
}
