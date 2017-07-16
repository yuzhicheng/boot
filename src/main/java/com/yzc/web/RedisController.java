package com.yzc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzc.dao.PersonDao;
import com.yzc.domain.Person;

@RestController
public class RedisController {

	@Autowired
	PersonDao personDao;

	@RequestMapping("/set/person/{id}")
	public void setPerson(@PathVariable String id) {
		Person person = new Person(id, "yzk", 34, "岳阳");
		personDao.savePersonObject(person);
		personDao.saveString();

	}

	@RequestMapping("/get/str")
	public String getStr() {
		return personDao.getString();
	}

	@RequestMapping("/get/person/{id}")
	public Person getPerson(@PathVariable String id) {
		return personDao.getPersonObject(id);
	}

}
