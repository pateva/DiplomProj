package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisDefenseCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenseDto;
import com.example.diplproj.data.dtos.ThesisDefensePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenseUsersDto;
import com.example.diplproj.services.contracts.ThesisDefenseService;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/defenses")
@RequiredArgsConstructor
public class ThesisDefenseController {
    private final ThesisDefenseService thesisDefenseService;

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping
    public ResponseEntity<Page<ThesisDefensePartialDto>> getThesisDefenses(@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "25") int size) {

        return new ResponseEntity<>(thesisDefenseService.getThesisDefenses(page, size), HttpStatus.OK);
    }

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/{id}")
    public ResponseEntity<ThesisDefenseDto> getThesisDefenseById(@PathVariable Long id) {

        return new ResponseEntity<>(thesisDefenseService.getThesisDefenseDto(id), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<ThesisDefenseDto> createThesisDefense(@RequestBody ThesisDefenseCreationDto thesisDefenseDto) {

        return new ResponseEntity<>(thesisDefenseService.createThesisDefense(thesisDefenseDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ThesisDefenseDto> addTeachersStudents(@PathVariable final Long id,
                                                                @RequestBody final ThesisDefenseUsersDto thesisDefenseUsersDto) {

        return new ResponseEntity<>(thesisDefenseService.addUsersToThesisDefense(id, thesisDefenseUsersDto), HttpStatus.OK);
    }
}
