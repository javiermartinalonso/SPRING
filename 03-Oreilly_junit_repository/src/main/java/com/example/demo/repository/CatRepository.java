package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domain.Cat;

@RepositoryRestResource
public interface CatRepository extends JpaRepository<Cat, Long> {
}
