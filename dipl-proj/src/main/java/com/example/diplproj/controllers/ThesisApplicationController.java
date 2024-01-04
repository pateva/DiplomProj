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
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/statusFilter")
    public ResponseEntity<Page<ThesisApplicationPartialDto>> getApplicationsByStatus(@RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "25") int size,
                                                                                     @RequestParam(defaultValue = "2") int status) {

        return new ResponseEntity<>(thesisApplicationService.getThesisApplicationsByStatus(page, size, status), HttpStatus.OK);
    }

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/{id}")
    public ResponseEntity<ThesisApplicationDto> getThesisApplicationById(@PathVariable final Long id) {

        return new ResponseEntity<>(thesisApplicationService.getThesisApplicationDtoById(id), HttpStatus.OK);
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

    @HasRoles(Roles.TEACHER)
    @PatchMapping("/{id}")
    public ResponseEntity<ThesisApplicationDto> updateThesisApplicationStatus(@PathVariable final Long id, @RequestParam(required = true) int status) {
        thesisApplicationService.updateThesisApplicationStatus(id, status);

        return new ResponseEntity<>(thesisApplicationService.getThesisApplicationDtoById(id), HttpStatus.ACCEPTED);
    }
}
