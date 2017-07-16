package com.yzc.dao;

import com.yzc.domain.Person;

public interface PersonDao {
	
	public void saveString();
	
	public void savePersonObject(Person person);
	
	public String getString();
	
	public Person getPersonObject(String id);

}
