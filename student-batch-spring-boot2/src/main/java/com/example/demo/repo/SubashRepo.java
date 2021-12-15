package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Subash;

@Repository
public interface SubashRepo extends JpaRepository<Subash, Integer> {
	
	@Query(value= "select age,city, GROUP_CONCAT(name) from subash_tbl s GROUP BY s.city", nativeQuery = true)
	public List<Object[]> getSubashDetails();

}
