package com.botscrew.university.service.impl;

import com.botscrew.university.entity.Lector;
import com.botscrew.university.repository.LectorRepository;
import com.botscrew.university.service.LectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorServiceImpl implements LectorService {

    final
    LectorRepository lectorRepository;

    public LectorServiceImpl(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Override
    public List<Lector> getLectorContaining(String name) {
        return lectorRepository.findAllByFullNameContainingIgnoreCase(name);
    }

}
