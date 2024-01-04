package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisReviewCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewDto;
import com.example.diplproj.services.contracts.ThesisReviewService;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ThesisReviewController {
    private final ThesisReviewService thesisReviewService;

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/{thesisId}")
    public ResponseEntity<Page<ThesisReviewDto>> getReviewsForThesis(@PathVariable final Long thesisId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "25") int size) {

        return new ResponseEntity<>(thesisReviewService.getThesisReviewsByThesis(thesisId, page, size), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<ThesisReviewDto> createThesisReview(@RequestBody ThesisReviewCreationDto thesisCreationDto,
                                                              @AuthenticationPrincipal Jwt jwt) {

        return new ResponseEntity<>(thesisReviewService.createThesisReview(thesisCreationDto, jwt.getClaim("email")), HttpStatus.OK);
    }
}
