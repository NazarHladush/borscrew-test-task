package com.botscrew.university.service;

import com.botscrew.university.entity.Degree;

import java.util.List;

public interface DegreeService {

    List<Degree> getDegreeContaining(String name);
}
