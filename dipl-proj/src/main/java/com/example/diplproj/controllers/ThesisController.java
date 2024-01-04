package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.dtos.ThesisDto;
import com.example.diplproj.services.contracts.ThesisService;
import com.example.diplproj.utils.Constants;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thesis")
@RequiredArgsConstructor
public class ThesisController {
    private final ThesisService thesisService;

    @HasRoles(Roles.STUDENT)
    @PostMapping
    public ResponseEntity<String> submitThesis(@RequestBody final ThesisCreationDto thesisCreationDto,
                                               @AuthenticationPrincipal Jwt jwt) {
        thesisService.createThesis(thesisCreationDto, jwt.getClaim("email"));
        return new ResponseEntity<>(Constants.SUCCESSFUL_SUBMISSION_MSG, HttpStatus.OK);
    }
}
