package com.example.diplproj.services.impl;

import com.example.diplproj.data.models.Department;
import com.example.diplproj.data.repositories.DepartmentRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.services.contracts.DepartmentService;
import com.example.diplproj.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public Department getById(Long id) {
        Optional<Department> departmentOpt = departmentRepository.getByDepartmentId(id);

        if(departmentOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Department", "id"));
        }

        return departmentOpt.get();
    }
}
