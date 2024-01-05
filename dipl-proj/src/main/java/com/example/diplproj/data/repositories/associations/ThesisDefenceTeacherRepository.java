package com.example.diplproj.data.repositories.associations;

import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenceTeacherKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThesisDefenceTeacherRepository extends JpaRepository<ThesisDefenseTeacher, DefenceTeacherKey> {
    @Query("SELECT t FROM ThesisDefenseTeacher t WHERE t.defenceTeacherKey.defenceId = :defenceId")
    List<ThesisDefenseTeacher> findAllByDefenceId(Long defenceId);
}

