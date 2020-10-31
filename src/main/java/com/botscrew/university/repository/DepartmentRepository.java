package com.botscrew.university.repository;

import com.botscrew.university.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

    List<Department> findByNameContainingIgnoreCase(String name);
}
