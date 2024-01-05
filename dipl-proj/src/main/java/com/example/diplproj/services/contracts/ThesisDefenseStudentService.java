package com.example.diplproj.services.contracts;

import com.example.diplproj.data.models.associations.ThesisDefenseStudent;

public interface ThesisDefenseStudentService {
    void saveAll(Iterable<ThesisDefenseStudent> thesisDefenseStudents);
    boolean existsById(Long studentId, Long defenceId);

    void deleteThesisDefenceStudent(Long studentId, Long defenceId);
}
