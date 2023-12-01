package com.project.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "department_description")
    private String departmentDescription;

//    public Department() {
//    }
//
//    public Department(Long id, String departmentName, String departmentDescription) {
//        this.id = id;
//        this.departmentName = departmentName;
//        this.departmentDescription = departmentDescription;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public String getDepartmentDescription() {
//        return departmentDescription;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public void setDepartmentDescription(String departmentDescription) {
//        this.departmentDescription = departmentDescription;
//    }

}
