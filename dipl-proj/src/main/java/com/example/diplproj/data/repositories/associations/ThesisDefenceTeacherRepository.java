package com.example.diplproj.data.repositories.associations;

import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenceTeacherKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisDefenceTeacherRepository extends JpaRepository<ThesisDefenseTeacher, DefenceTeacherKey> {
}
