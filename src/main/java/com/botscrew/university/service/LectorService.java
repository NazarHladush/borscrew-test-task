package com.botscrew.university.service;

import com.botscrew.university.entity.Lector;

import java.util.List;

public interface LectorService {

    List<Lector> getLectorContaining(String name);
}
