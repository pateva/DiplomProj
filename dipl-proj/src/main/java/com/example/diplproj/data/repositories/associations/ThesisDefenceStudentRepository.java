package com.example.diplproj.data.repositories.associations;

import com.example.diplproj.data.models.associations.ThesisDefenseStudent;
import com.example.diplproj.data.models.associations.keys.DefenceStudentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisDefenceStudentRepository extends JpaRepository<ThesisDefenseStudent, DefenceStudentKey> {
}
