package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisApplicationCreationDto;
import com.example.diplproj.data.dtos.ThesisApplicationDto;
import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.mappers.ThesisApplicationMapper;
import com.example.diplproj.services.contracts.ThesisApplicationService;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ThesisApplicationController {
    private final ThesisApplicationService thesisApplicationService;
    private final ThesisApplicationMapper thesisApplicationMapper;

    @GetMapping("/")
    public String getHome(@AuthenticationPrincipal OAuth2User principal) {
        return "home";
    }

    @HasRoles(Roles.TEACHER)
    @GetMapping
    public ResponseEntity<Page<ThesisApplicationPartialDto>> getApplications(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "25") int size) {

        return new ResponseEntity<>(thesisApplicationService.getThesisApplications(page, size), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<ThesisApplicationDto> createThesisApplication(@RequestBody final ThesisApplicationCreationDto thesisApplicationDto,
                                                                        @AuthenticationPrincipal Jwt jwt) {

        return new ResponseEntity<>(
                thesisApplicationMapper
                        .toThesisApplicationDto(thesisApplicationService
                                .createThesisApplication(thesisApplicationDto, jwt.getClaims().get("email").toString())),
                HttpStatus.OK);

    }
}
