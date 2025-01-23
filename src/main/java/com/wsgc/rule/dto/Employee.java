package com.wsgc.rule.dto;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private Integer employeeID;
    private String employeeName;
    private List<Project> projects;
    private Integer Salary;
}
