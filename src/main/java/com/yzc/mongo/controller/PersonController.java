package com.yzc.mongo.controller;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzc.mongo.domain.Location;
import com.yzc.mongo.domain.Person;
import com.yzc.mongo.repository.PersonRepository;

@RestController
@RequestMapping("/mongo")
public class PersonController {

	@Qualifier("peopleRepository")
	@Autowired
	PersonRepository personRepository;

	@RequestMapping("/save")
	public Person save() {
		Person p = new Person("yzc", 23);
		Collection<Location> localtions = new LinkedHashSet<>();
		Location location = new Location("上海", "1990");
		Location location2 = new Location("哈哈", "1991");
		Location location3 = new Location("上到的海", "1992");
		Location location4 = new Location("上海放到", "1993");
		Location location5 = new Location("上海", "1994");
		localtions.add(location);
		localtions.add(location2);
		localtions.add(location3);
		localtions.add(location4);
		localtions.add(location5);
		p.setLocations(localtions);
		return personRepository.save(p);
	}

	@RequestMapping("/q1")
	public Person q1(String name) {

		return personRepository.findByName(name);
	}

	@RequestMapping("/q2")
	public List<Person> q2(Integer age) {
		return personRepository.withQueryFindByAge(age);
	}

}
