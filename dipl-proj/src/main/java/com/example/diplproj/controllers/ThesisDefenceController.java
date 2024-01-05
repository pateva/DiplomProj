package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.dtos.ThesisDefencePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenceUsersDto;
import com.example.diplproj.services.contracts.ThesisDefenseService;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/defences")
@RequiredArgsConstructor
public class ThesisDefenceController {
    private final ThesisDefenseService thesisDefenseService;

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping
    public ResponseEntity<Page<ThesisDefencePartialDto>> getThesisDefenses(@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "25") int size) {

        return new ResponseEntity<>(thesisDefenseService.getThesisDefences(page, size), HttpStatus.OK);
    }

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/{id}")
    public ResponseEntity<ThesisDefenceDto> getThesisDefenseById(@PathVariable Long id) {

        return new ResponseEntity<>(thesisDefenseService.getThesisDefenceDto(id), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<ThesisDefenceDto> createThesisDefense(@RequestBody ThesisDefenceCreationDto thesisDefenseDto) {

        return new ResponseEntity<>(thesisDefenseService.createThesisDefence(thesisDefenseDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ThesisDefenceDto> addTeachersStudents(@PathVariable final Long id,
                                                                @RequestBody final ThesisDefenceUsersDto thesisDefenceUsersDto) {

        return new ResponseEntity<>(thesisDefenseService.addUsersToThesisDefence(id, thesisDefenceUsersDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisDefenceDto> updateThesisDefense(@PathVariable final Long id,
                                                                @RequestBody final ThesisDefenceCreationDto thesisDefenceCreationDto) {
        return new ResponseEntity<>(thesisDefenseService.updateThesisDefence(id, thesisDefenceCreationDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}/student/{studentId}")
    public ResponseEntity<ThesisDefenceDto> removeStudentFromDefense(@PathVariable final Long id,
                                                                     @PathVariable final Long studentId) {

        return new ResponseEntity<>(thesisDefenseService.removeStudentFromDefence(id, studentId), HttpStatus.ACCEPTED);
    }
}
