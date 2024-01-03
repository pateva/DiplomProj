package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Page<Student> findAll(Pageable pageable);
    Optional<Student> findByUserId(Long id);
    boolean existsByEmail(String email);
}
