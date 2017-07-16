package com.yzc.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.yzc.mongo.domain.Person;

@Component(value="peopleRepository") //加这个注解主要是与repository包中的PersonRepository作区分
public interface PersonRepository extends MongoRepository<Person, String> {

	Person findByName(String name);

	@Query("{'age':?0}")
	List<Person> withQueryFindByAge(Integer age);
}
