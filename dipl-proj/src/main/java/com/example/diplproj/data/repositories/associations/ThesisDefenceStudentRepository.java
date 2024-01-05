package com.example.diplproj.data.repositories.associations;

import com.example.diplproj.data.models.associations.ThesisDefenceStudent;
import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenceStudentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThesisDefenceStudentRepository extends JpaRepository<ThesisDefenceStudent, DefenceStudentKey> {
    @Query("SELECT t FROM ThesisDefenceStudent t WHERE t.thesisDefenceStudentKey.defenceId = :defenceId")
    List<ThesisDefenceStudent> findAllByDefenceId(Long defenceId);

}
