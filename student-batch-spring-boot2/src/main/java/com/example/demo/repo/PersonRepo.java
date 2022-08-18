package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

@Repository
//Author Subash Pernandas Savari
public interface PersonRepo extends JpaRepository<Person, Integer>{

}
