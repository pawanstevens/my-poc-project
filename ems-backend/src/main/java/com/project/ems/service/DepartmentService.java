package com.project.ems.service;
import java.util.List;
import com.project.ems.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto updateDepartmentById(Long departmentId, DepartmentDto updatedDepartment);

    void deleteDepartment(Long departmentId);
}
