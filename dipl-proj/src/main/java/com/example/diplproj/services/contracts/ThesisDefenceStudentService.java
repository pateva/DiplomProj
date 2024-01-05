package com.example.diplproj.services.contracts;

import com.example.diplproj.data.models.associations.ThesisDefenceStudent;

import java.util.List;

public interface ThesisDefenceStudentService {
    void saveAll(Iterable<ThesisDefenceStudent> thesisDefenseStudents);
    boolean existsById(Long studentId, Long defenceId);
    void deleteThesisDefenceStudent(Long studentId, Long defenceId);
    List<ThesisDefenceStudent> getByDefenceId(Long defenceId);
}
