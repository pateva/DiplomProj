package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> getByDepartmentId(Long id);
}
