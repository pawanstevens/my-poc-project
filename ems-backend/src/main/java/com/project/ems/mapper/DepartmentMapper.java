package com.project.ems.mapper;

import com.project.ems.dto.DepartmentDto;
import com.project.ems.entity.Department;

public class DepartmentMapper {

    //Convert Department JPA Entity into DepartmentDto
    public static DepartmentDto mapToDepartmentDto(Department department){

        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }


    //Convert DepartmentDto to Department JPA Entity
    public static Department mapToDepartment(DepartmentDto departmentDto){

        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }
}
