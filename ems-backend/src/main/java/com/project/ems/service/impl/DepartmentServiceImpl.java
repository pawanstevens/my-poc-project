package com.project.ems.service.impl;

import com.project.ems.dto.DepartmentDto;
import com.project.ems.entity.Department;
import com.project.ems.exception.ResourceNotFoundException;
import com.project.ems.mapper.DepartmentMapper;
import com.project.ems.repository.DepartmentRepository;
import com.project.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.*;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        //converting DepartmentDto to Department Entity to perform database operations
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        //performing database operations
        Department savedDepartment = departmentRepository.save(department);

        //converting Department back to DepartmentDto to send to client
        DepartmentDto departmentDto1 = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        //sending DepartmentDto back to client
        return departmentDto1;
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department does not exist with a given Id: " + departmentId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments(){

        List<Department> departments = departmentRepository.findAll();

        //now we need to convert this jpa entity to DepartmentDto to return
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }


    @Override
    public DepartmentDto updateDepartmentById(Long departmentId, DepartmentDto updatedDepartment){

        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department does not exist with a given Id: " + departmentId));

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }


    @Override
    public void deleteDepartment(Long departmentId){

        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department does not exist with a given Id: " + departmentId));

        departmentRepository.deleteById(departmentId);
    }
}
