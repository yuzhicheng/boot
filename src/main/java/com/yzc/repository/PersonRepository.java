package com.yzc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

import com.yzc.domain.Person;

/**
 * 定制节点路径。Spring Data REST 的默认规则就是在实体类之后加s来形成路径 ，如localhost:8080/persons。
 * 在实体类Repository上使用@RepositoryRestResource注解的path属性修改
 * @author yzc
 * @date 2017年5月9日  下午6:47:57
 * @version 1.0
 */
 @RepositoryRestResource(path="people") 
 @Component(value="PersonRepository") //加这个注解主要是与mongo包中的PersonRepository作区分
public interface PersonRepository extends CustomRepository<Person, String>{
	
	List<Person> findByAddress(String name);
	
	List<Person> findByNameAndAddress(String name,String address);
	
	@Query("select p from Person p where p.name=:name and p.address=:address")
	List<Person> withNameAndAddressQuery(@Param("name")String name,@Param("address") String address);
	
	List<Person> withNameAndAddressNamedQuery(String name,String address);
	
	//spring data rest 将此方法暴露为REST资源
	@RestResource(path="nameStartsWith",rel="nameStartsWith")
	List<Person> findByNameStartsWith(@Param("name") String name);

}
