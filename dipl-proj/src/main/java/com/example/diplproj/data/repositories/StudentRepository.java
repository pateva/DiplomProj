package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Page<Student> findAll(Pageable pageable);
    Optional<Student> findByUserId(Long id);
    boolean existsByEmail(String email);
    boolean existsByFacNumber(String facNumber);
}
