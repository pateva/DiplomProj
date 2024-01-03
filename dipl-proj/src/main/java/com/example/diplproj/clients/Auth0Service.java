package com.example.diplproj.clients;

import java.util.List;

public interface Auth0Service {
    void createUser(String email, char[] password, List<String> roleIds);
    void deleteUser(String email);
    void updateUserEmail(String emailOld, String emailNew);
}
