package com.botscrew.university.service;

import com.botscrew.university.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    String getHeadOfDepartment(String departmentName);

    Map<String, Integer> getDepartmentStatistics(String departmentName);

    String getDepartmentsName();

    double getAverageSalary(String departmentName);

    long getCountOfEmployee(String departmentName);

    List<Department> getDepartmentContaining(String name);
}
