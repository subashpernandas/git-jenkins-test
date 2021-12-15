package com.example.demo.processor;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepo;

@Component
public class PersonItemWriter implements ItemWriter<Person> {
	
	@Autowired private PersonRepo personRepo;

	@Override
	public void write(List<? extends Person> person) throws Exception {
		System.out.println("Data saved successfully::::::::"+ person);
		personRepo.saveAll(person);
	}

}
