package com.example.diplproj.clients;

public interface Auth0Service {
    void createUser(String email, char[] password);
}
