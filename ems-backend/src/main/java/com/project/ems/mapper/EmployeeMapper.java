package com.project.ems.mapper;

import com.project.ems.dto.EmployeeDto;
import com.project.ems.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){

        //getting all the values from employee jpa entity and let us set those values to the EmployeeDto
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        //getting all values from employeeDto and setting it to Employee
      Employee employee = new Employee();
      employee.setId(employeeDto.getId());
      employee.setFirstName(employeeDto.getFirstName());
      employee.setLastName(employeeDto.getLastName());
      employee.setEmail(employeeDto.getEmail());
      return employee;
    }

}
