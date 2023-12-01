package com.project.ems.controller;

import com.project.ems.dto.DepartmentDto;
import com.project.ems.service.DepartmentService;
import com.project.ems.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentController() {
    }

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    //Build Add Department REST API
    @PostMapping()
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){

        DepartmentDto savedDepartmentDto = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }


    //Build Get Department REST API
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){

        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }



    //Build Get ALL Department REST API
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){

        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentDtos);
    }


    //Build Update Department REST API
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto departmentDto){

        DepartmentDto departmentDto1 = departmentService.updateDepartmentById(departmentId, departmentDto);

        return ResponseEntity.ok(departmentDto1);
    }



    //Build Delete Department REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){

        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department Successfully Deleted");
    }
}
