package com.example.diplproj.data.repositories.associations;

import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenseTeacherKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisDefenseTeacherRepository extends JpaRepository<ThesisDefenseTeacher, DefenseTeacherKey> {
}
