package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisDefenseCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenseDto;
import com.example.diplproj.data.models.ThesisDefense;
import com.example.diplproj.services.contracts.ThesisDefenseService;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/defenses")
@RequiredArgsConstructor
public class ThesisDefenseController {
    private final ThesisDefenseService thesisDefenseService;

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<ThesisDefenseDto> createThesisDefense(@RequestBody ThesisDefenseCreationDto thesisDefenseDto) {

        return new ResponseEntity<>(thesisDefenseService.createThesisDefense(thesisDefenseDto), HttpStatus.OK);
    }
}
