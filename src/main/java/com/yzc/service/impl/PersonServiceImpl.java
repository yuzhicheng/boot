package com.yzc.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzc.domain.Person;
import com.yzc.repository.PersonRepository;
import com.yzc.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	@Transactional(rollbackFor = { IllegalArgumentException.class })
	public Person savePersonWithRollBack(Person person) {

		person.setId(UUID.randomUUID().toString());
		Person p = personRepository.save(person);

		if (person.getName().equals("xx")) {
			throw new IllegalArgumentException("xx已经存在，数据将回滚");
		}
		return p;
	}

	@Override
	@Transactional(noRollbackFor = { IllegalArgumentException.class })
	public Person savePersonWithoutRollBack(Person person) {
		person.setId(UUID.randomUUID().toString());
		Person p = personRepository.save(person);

		if (person.getName().equals("xx")) {
			throw new IllegalArgumentException("xx虽然已经存在，但数据将不会回滚");
		}
		return p;
	}

	@Override
	@CachePut(value="people",key="#person.id")
	public Person save(Person person) {

		Person p = personRepository.save(person);
		System.out.println("为id、key为："+p.getId()+" 做了缓存");
		return p;
	}
	
	@Override
	@CacheEvict(value="people")
	public void delete(String id) {
		personRepository.delete(id);
		System.out.println("删除了id、key为："+id+" 的数据缓存");
	}
	
	@Override
	@Cacheable(value="people",key="#id")
	public Person findOne(String id) {
		Person person = personRepository.findOne(id);
		System.out.println("为id、key为："+id+" 做了缓存");
		return person;
	}

	@Override
	public List<Person> findByAddress(String address) {

		List<Person> list = personRepository.findByAddress(address);
		return list;
	}

	@Override
	public List<Person> findByNameAndAddress(String name, String address) {

		List<Person> p = personRepository.findByNameAndAddress(name, address);
		return p;
	}

	@Override
	public List<Person> withNameAndAddressQuery(String name, String address) {

		List<Person> p = personRepository.withNameAndAddressQuery(name, address);
		return p;
	}

	@Override
	public List<Person> withNameAndAddressNamedQuery(String name, String address) {

		List<Person> p = personRepository.withNameAndAddressNamedQuery(name, address);
		return p;
	}

}
