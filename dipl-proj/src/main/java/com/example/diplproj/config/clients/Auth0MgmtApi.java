package com.example.diplproj.config.clients;

import com.auth0.client.auth.AuthAPI;
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

    @Bean
    public AuthAPI createAuth0MgmtApi() {
        System.err.println(domain + "\t" + clientId + "\t" + clientSecret);
        return AuthAPI.newBuilder(domain, clientId, clientSecret).build();
    }
}
