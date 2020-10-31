package com.botscrew.university.service.impl;

import com.botscrew.university.entity.Degree;
import com.botscrew.university.repository.DegreeRepository;
import com.botscrew.university.service.DegreeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeServiceImpl implements DegreeService {

    final DegreeRepository degreeRepository;

    public DegreeServiceImpl(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public List<Degree> getDegreeContaining(String name) {
        return degreeRepository.findAllByNameContainingIgnoreCase(name);
    }
}
