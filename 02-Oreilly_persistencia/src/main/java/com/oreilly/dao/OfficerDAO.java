package com.oreilly.dao;

import java.util.List;
import java.util.Optional;

import com.oreilly.entities.Officer;

public interface OfficerDAO {
    Officer save(Officer officer);
    Optional<Officer> findById(Integer id);
    List<Officer> findAll();
    long count();
    void delete(Officer officer);
    boolean existsById(Integer id);
}
