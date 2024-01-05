package com.example.diplproj.services.contracts;

import com.example.diplproj.data.models.associations.ThesisDefenceStudent;

public interface ThesisDefenceStudentService {
    void saveAll(Iterable<ThesisDefenceStudent> thesisDefenseStudents);
    boolean existsById(Long studentId, Long defenceId);

    void deleteThesisDefenceStudent(Long studentId, Long defenceId);
}
