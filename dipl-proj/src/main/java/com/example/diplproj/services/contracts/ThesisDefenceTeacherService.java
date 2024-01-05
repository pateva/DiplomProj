package com.example.diplproj.services.contracts;

import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;

public interface ThesisDefenceTeacherService {
    void saveAll(Iterable<ThesisDefenseTeacher> thesisDefenseTeachers);

    boolean existsById(Long teacherId, Long defenceId);

    void deleteThesisDefenceTeacher(Long teacherId, Long defenceId);

}
