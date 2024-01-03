package com.example.diplproj.clients;

public interface Auth0Service {
    void createUser(String email, char[] password);
    void deleteUser(String email);
    void updateUserEmail(String emailOld, String emailNew);
}
