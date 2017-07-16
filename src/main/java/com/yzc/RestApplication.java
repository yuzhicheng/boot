package com.yzc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yzc.config.AuthorSettings;
import com.yzc.domain.Person;
import com.yzc.repository.PersonRepository;
import com.yzc.service.PersonService;

@RestController
public class RestApplication {

	@Value("${book.author}")
	private String bookAuthor;
	@Value("${book.name}")
	private String bookName;

	@Autowired
	private AuthorSettings authorSettings;
	
//	@Autowired
//	private HelloService helloService;
//	@RequestMapping("/autowired")
//	String autowired() {
//		return helloService.sayHello();
//	}
	
	@RequestMapping("/haha")
	String index() {
		return "book name is:" + bookName + ", and book author is:" + bookAuthor + authorSettings.getName() + ";"
				+ authorSettings.getAge();

	}

	@RequestMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Person search(String personName) {
		return new Person(UUID.randomUUID().toString(), "xx", 11, "haha");

	}
	@RequestMapping(value = "/find", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Person find(String personName) {
		return new Person(UUID.randomUUID().toString(), "xx", 11, "haha");

	}

	@Qualifier("PersonRepository")
	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonService personService;

	@RequestMapping("/save")
	public Person save(String name, String address, Integer age) {

		Person person = new Person();
		person.setId(UUID.randomUUID().toString());
		person.setAddress(address);
		person.setAge(age);
		person.setName(name);
		Person p = personService.save(person);
		return p;
	}

	@RequestMapping(value = "/evit", method = RequestMethod.DELETE)
	public void delete(String id) {

		personService.delete(id);
	}

	@RequestMapping("/able")
	public Person cacheable(String id) {

		Person p = personService.findOne(id);
		return p;
	}

	@RequestMapping("/list")
	public List<Person> getlist(String address) {

		List<Person> list = personService.findByAddress(address);
		return list;
	}

	@RequestMapping("/q1")
	public List<Person> q1(String name, String address) {

		List<Person> p = personService.findByNameAndAddress(name, address);
		return p;
	}

	@RequestMapping("/q2")
	public List<Person> q2(String name, String address) {

		List<Person> p = personService.withNameAndAddressQuery(name, address);
		return p;
	}

	@RequestMapping("/q3")
	public List<Person> q3(String name, String address) {

		List<Person> p = personService.withNameAndAddressNamedQuery(name, address);
		return p;
	}

	@RequestMapping("/sort")
	public List<Person> sort() {
		List<Person> people = personRepository.findAll(new Sort(Direction.ASC, "age"));
		return people;
	}

	@RequestMapping("/page")
	public Page<Person> page() {

		Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));

		return pagePeople;
	}

	@RequestMapping("/auto")
	public Page<Person> auto(Person person) {

		Page<Person> pagePeople = personRepository.findByAuto(person, new PageRequest(0, 10));

		return pagePeople;
	}

	/**
	 * 使用Spring Data REST：（以Person为例） Spring Data REST 的默认规则就是在实体类之后加s来形成路径
	 * ，如localhost:8080/persons,由于进行了配置，访问url改变了 1、获取列表：GET
	 * localhost:8080/rest/people 2、获取单一对象：GET
	 * localhost:8080/rest/people/1(1为id) 3、查询：GET
	 * localhost:8080/rest/people/search/nameStartsWith?name 4、分页：GET
	 * localhost:8080/rest/people/?page=1&size=2 5、排序：GET
	 * localhost:8080/rest/people/?sort=age,desc 8、保存：POST
	 * localhost:8080/rest/people 数据：{"name":"rest","address":"福建", "age":12}
	 * 9、更新：PUT localhost:8080/rest/people/1
	 * 数据：{"name":"rest","address":"福建","age":12} 10、删除：DELETE
	 * localhost:8080/rest/people/1
	 */

	@RequestMapping(value = "/rollback", method = RequestMethod.POST)
	public Person rollback(@RequestBody Person person) {

		return personService.savePersonWithRollBack(person);
	}

	@RequestMapping(value = "/norollback", method = RequestMethod.POST)
	public Person noRollback(@RequestBody Person person) {

		return personService.savePersonWithoutRollBack(person);
	}
}
