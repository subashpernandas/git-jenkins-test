package com.example.demo.processor;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.model.Person;

@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person>{

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(Person person) throws Exception {
		Person personUpdate = new Person(person.getFirstName(), person.getLastName(), person.getEmail(), 
				person.getAge());
		personUpdate.setDateOfEntry(new Date());
        LOGGER.info("Converting ( {} ) into ( {} )", person, personUpdate);
		return personUpdate;
	}

	
}
