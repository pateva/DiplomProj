package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewDto;
import com.example.diplproj.services.contracts.ThesisReviewService;
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
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ThesisReviewController {
    private final ThesisReviewService thesisReviewService;

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<ThesisReviewDto> createThesisReview(@RequestBody ThesisReviewCreationDto thesisCreationDto,
                                                              @AuthenticationPrincipal Jwt jwt) {

        return new ResponseEntity<>(thesisReviewService.createThesisReview(thesisCreationDto, jwt.getClaim("email")), HttpStatus.OK);
    }

}
