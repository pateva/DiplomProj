package com.example.diplproj.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThesisApplicationController {
    @GetMapping("/")
    public String getHome(@AuthenticationPrincipal OidcUser principal) {
        return "home";
    }

    @GetMapping("/abc")
    public String getAbc(@AuthenticationPrincipal OidcUser principal) {
        return "home";
    }

    @GetMapping("/login/oauth2/code/okta")
    public Boolean oktaLogin(@AuthenticationPrincipal OidcUser principal) {
        return true;
    }

    @GetMapping(value = "/public")
    public String publicEndpoint() {
        return "All good. You DO NOT need to be authenticated to call /api/public.";
    }

    @GetMapping(value = "/private")
    public String privateEndpoint() {
        return "All good. You can see this because you are Authenticated.";
    }

    @GetMapping(value = "/private-scoped")
    public String privateScopedEndpoint() {
        return "All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope";
    }
}
