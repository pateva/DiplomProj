package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.services.contracts.ThesisApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/applications")
@RequiredArgsConstructor
public class ThesisApplicationController {
    private final ThesisApplicationService thesisApplicationService;

    @GetMapping("/")
    public String getHome(@AuthenticationPrincipal OAuth2User principal) {
        return "home";
    }

    @GetMapping
    public ResponseEntity<Page<ThesisApplicationPartialDto>> getApplications(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "25") int size,
                                                                             @AuthenticationPrincipal User user) {

        return new ResponseEntity<>(thesisApplicationService.getThesisApplications(page, size), HttpStatus.OK);
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
