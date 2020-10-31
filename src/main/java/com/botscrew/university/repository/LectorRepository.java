package com.botscrew.university.repository;

import com.botscrew.university.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    Lector findByFullName(String fullName);

    List<Lector> findAllByFullNameContainingIgnoreCase(String fullName);
}
