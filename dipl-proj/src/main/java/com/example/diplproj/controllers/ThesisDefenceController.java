package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.dtos.ThesisDefencePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenceUsersDto;
import com.example.diplproj.services.contracts.ThesisDefenceService;
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
    private final ThesisDefenceService thesisDefenceService;

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping
    public ResponseEntity<Page<ThesisDefencePartialDto>> getThesisDefences(@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "25") int size) {

        return new ResponseEntity<>(thesisDefenceService.getThesisDefences(page, size), HttpStatus.OK);
    }

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/{id}")
    public ResponseEntity<ThesisDefenceDto> getThesisDefenceById(@PathVariable Long id) {

        return new ResponseEntity<>(thesisDefenceService.getThesisDefenceDto(id), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<ThesisDefenceDto> createThesisDefence(@RequestBody ThesisDefenceCreationDto thesisDefenseDto) {

        return new ResponseEntity<>(thesisDefenceService.createThesisDefence(thesisDefenseDto), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PatchMapping("/{id}")
    public ResponseEntity<ThesisDefenceDto> addTeachersStudents(@PathVariable final Long id,
                                                                @RequestBody final ThesisDefenceUsersDto thesisDefenceUsersDto) {

        return new ResponseEntity<>(thesisDefenceService.addUsersToThesisDefence(id, thesisDefenceUsersDto), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PutMapping("/{id}")
    public ResponseEntity<ThesisDefenceDto> updateThesisDefence(@PathVariable final Long id,
                                                                @RequestBody final ThesisDefenceCreationDto thesisDefenceCreationDto) {
        return new ResponseEntity<>(thesisDefenceService.updateThesisDefence(id, thesisDefenceCreationDto), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteThesisDefence(@PathVariable final Long id) {

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @HasRoles(Roles.TEACHER)
    @DeleteMapping("{id}/student/{studentId}")
    public ResponseEntity<ThesisDefenceDto> removeStudentFromDefence(@PathVariable final Long id,
                                                                     @PathVariable final Long studentId) {

        return new ResponseEntity<>(thesisDefenceService.removeStudentFromDefence(id, studentId), HttpStatus.ACCEPTED);
    }

    @HasRoles(Roles.TEACHER)
    @DeleteMapping("{id}/teacher/{teacherId}")
    public ResponseEntity<ThesisDefenceDto> removeTeacherFromThesisDefence(@PathVariable final Long id,
                                                                           @PathVariable final Long teacherId) {
        return new ResponseEntity<>(thesisDefenceService.removeTeacherFromDefence(id, teacherId), HttpStatus.OK);
    }
}
