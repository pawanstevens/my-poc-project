package com.project.ems.service.impl;

import com.project.ems.entity.Employee;
import com.project.ems.dto.EmployeeDto;
import com.project.ems.exception.ResourceNotFoundException;
import com.project.ems.mapper.EmployeeMapper;
import com.project.ems.repository.DepartmentRepository;
import com.project.ems.entity.Department;
import com.project.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ems.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;


    private DepartmentRepository departmentRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //we have converted employeeDto to employee jpa entity
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException("Department does not exist with this given Id: " + employeeDto.getDepartmentId()));

        employee.setDepartment(department);
        //lets save the employee jpa entity into database
        Employee savedEmployee = employeeRepository.save(employee);

        //need to send savedEmployee back to client
        //so lets convert savedEmployee jpa entity into EmployeeDto
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not Exist for this given Id" + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        //before returning we have to convert employee jpa entity into employeeDto
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        //first we need to check if the employeeId exists or not, if not then we have to throw exception
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee does not Exist for this given Id" + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException("Department does not exist with this given Id: " + updatedEmployee.getDepartmentId()));

        employee.setDepartment(department);

        //this save method is for both update and insert data for first time
        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        //first we need to check if the employeeId exists or not, if not then we have to throw exception
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee does not Exist for this given Id" + employeeId));

        employeeRepository.deleteById(employeeId);
    }


}
