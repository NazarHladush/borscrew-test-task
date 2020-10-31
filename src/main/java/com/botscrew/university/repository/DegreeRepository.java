package com.botscrew.university.repository;

import com.botscrew.university.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {

    Degree findByName(String name);

    List<Degree> findAllByNameContainingIgnoreCase(String name);
}
