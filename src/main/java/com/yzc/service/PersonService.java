package com.yzc.service;

import java.util.List;

import com.yzc.domain.Person;

public interface PersonService {
	
	public Person save(Person person);
	
	public void delete(String id);

	public Person findOne(String id);
	
	public Person savePersonWithRollBack(Person person);
	
	public Person savePersonWithoutRollBack(Person person);

	public List<Person> findByAddress(String address);

	public List<Person> findByNameAndAddress(String name, String address);

	public List<Person> withNameAndAddressQuery(String name, String address);

	public List<Person> withNameAndAddressNamedQuery(String name, String address);

}
