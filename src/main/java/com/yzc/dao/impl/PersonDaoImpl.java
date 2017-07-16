package com.yzc.dao.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.yzc.dao.PersonDao;
import com.yzc.domain.Person;

@Repository
public class PersonDaoImpl implements PersonDao{

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@Resource(name="stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;
	
	@Resource(name="redisTemplate")
	ValueOperations<Object, Object> valOps;
	
	@Override
	public void saveString() {
		
		valOpsStr.set("xx", "yy");
	}

	@Override
	public void savePersonObject(Person person) {
		valOps.set(person.getId(), person);
		
	}

	@Override
	public String getString() {
		
		return valOpsStr.get("xx");
	}

	@Override
	public Person getPersonObject(String id) {
		
		return (Person) valOps.get(id);
	}

}
