package com.botscrew.university.service.impl;

import com.botscrew.university.entity.Degree;
import com.botscrew.university.entity.Department;
import com.botscrew.university.entity.Lector;
import com.botscrew.university.repository.DegreeRepository;
import com.botscrew.university.repository.DepartmentRepository;
import com.botscrew.university.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    final
    DepartmentRepository departmentRepository;
    final DegreeRepository degreeRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DegreeRepository degreeRepository) {
        this.departmentRepository = departmentRepository;
        this.degreeRepository = degreeRepository;
    }

    @Override
    public String getHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department != null) {
            Lector headOfDepartment = department.getHeadOfDepartment();
            return String.format("Head of %s department is %s%n", departmentName, headOfDepartment.getFullName());

        } else {
            return String.format("Department %s not found.%n", departmentName);
        }
    }

    @Override
    public Map<String, Integer> getDepartmentStatistics(String departmentName) {
        Map<String, Integer> departmentStatistic = new HashMap<>();
        List<Degree> degrees = degreeRepository.findAll();
        degrees.forEach(degree -> departmentStatistic.put(degree.getName(), 0));

        Department department = departmentRepository.findByName(departmentName);
        Set<Lector> lectors = department.getLectors();
        lectors.forEach(lector -> departmentStatistic.put(lector.getDegree().getName(),
                departmentStatistic.get(lector.getDegree().getName()) + 1));
        return departmentStatistic;
    }

    @Override
    public double getAverageSalary(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        OptionalDouble average = department.getLectors().stream().mapToDouble(Lector::getSalary).average();
        return average.orElse(0);
    }

    @Override
    public long getCountOfEmployee(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        return department.getLectors().size();
    }

    @Override
    public List<Department> getDepartmentContaining(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public String getDepartmentsName() {
        List<Department> departments = departmentRepository.findAll();
        List<String> departmentsName = departments.stream().map(Department::getName).collect(Collectors.toList());
        return String.join(", ", departmentsName);
    }

}
