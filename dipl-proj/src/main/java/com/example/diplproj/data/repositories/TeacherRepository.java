package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Page<Teacher> findAll(Pageable pageable);
    boolean existsByEmail(String email);
    Optional<Teacher> findByEmail(String email);
}
