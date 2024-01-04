package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.Thesis;
import com.example.diplproj.utils.enums.ThesisStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    Page<Thesis> findByStudentAndThesisStatus(Student student, ThesisStatus status, Pageable pageable);

    Page<Thesis> findByStudent(Student student, Pageable pageable);

    Page<Thesis> findByTeacherAndThesisStatus(Teacher teacher, ThesisStatus status, Pageable pageable);

    Page<Thesis> findByTeacher(Teacher teacher, Pageable pageable);
}
