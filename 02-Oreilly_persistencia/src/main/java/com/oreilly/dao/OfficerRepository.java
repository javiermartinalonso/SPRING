package com.oreilly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oreilly.entities.Officer;
import com.oreilly.entities.Rank;

public interface OfficerRepository extends JpaRepository<Officer, Integer>{
	

	List<Officer> findByLast(String last);
	List<Officer> findAllByRankAndLastLike(Rank rank, String last);
	

}
