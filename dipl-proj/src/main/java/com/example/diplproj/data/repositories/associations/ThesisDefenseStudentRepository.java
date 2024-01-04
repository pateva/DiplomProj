package com.example.diplproj.data.repositories.associations;

import com.example.diplproj.data.models.associations.ThesisDefenseStudent;
import com.example.diplproj.data.models.associations.keys.DefenseStudentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisDefenseStudentRepository extends JpaRepository<ThesisDefenseStudent, DefenseStudentKey> {
}
