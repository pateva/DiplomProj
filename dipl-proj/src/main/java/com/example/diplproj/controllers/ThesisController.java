package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.dtos.ThesisDto;
import com.example.diplproj.data.dtos.ThesisPartialDto;
import com.example.diplproj.services.contracts.ThesisService;
import com.example.diplproj.utils.Constants;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/thesis")
@RequiredArgsConstructor
public class ThesisController {
    private final ThesisService thesisService;

    @HasRoles({Roles.STUDENT, Roles.TEACHER})
    @GetMapping("/{id}")
    public ResponseEntity<ThesisDto> getThesisById(@PathVariable final Long id,
                                                   @AuthenticationPrincipal Jwt jwt) {

        return new ResponseEntity<>(thesisService.getThesisById(id, jwt.getClaimAsStringList("user_roles").get(0), jwt.getClaim("email")), HttpStatus.OK);
    }

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/byUser")
    public ResponseEntity<Page<ThesisPartialDto>> getThesisByUser(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "25") int size,
                                                                  @RequestParam(required = false) final Integer status,
                                                                  @AuthenticationPrincipal Jwt jwt) {

        return new ResponseEntity<>(thesisService.findByUserAndStatus(jwt.getClaimAsStringList("user_roles").get(0),
                jwt.getClaim("email"), status, page, size), HttpStatus.OK);
    }

    @HasRoles(Roles.STUDENT)
    @PostMapping
    public ResponseEntity<String> submitThesis(@RequestBody final ThesisCreationDto thesisCreationDto,
                                               @AuthenticationPrincipal Jwt jwt) {
        thesisService.createThesis(thesisCreationDto, jwt.getClaim("email"));
        return new ResponseEntity<>(Constants.SUCCESSFUL_SUBMISSION_MSG, HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PatchMapping("/{id}")
    public ResponseEntity<ThesisDto> updateThesis(@PathVariable final Long id,
                                                  @RequestParam (required = false) final Integer status,
                                                  @RequestParam (required = false) final Integer grade) {

        return new ResponseEntity<>(thesisService.updateThesisStatus(id, status, grade), HttpStatus.ACCEPTED);
    }
}
