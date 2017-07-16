package com.yzc;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yzc.domain.Msg;
import com.yzc.domain.Person;


@Controller
public class TestApplication {
	
	
	@RequestMapping("/test")
	String test(Model model){
		
		Person single=new Person(UUID.randomUUID().toString(),"aa",11,"haha");	
		List<Person> pList=new ArrayList<Person>();
		Person p1=new Person(UUID.randomUUID().toString(),"xx",11,"haha");
		Person p2=new Person(UUID.randomUUID().toString(),"yy",22,"haha");
		Person p3=new Person(UUID.randomUUID().toString(),"zz",33,"haha");
		
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		
		model.addAttribute("singlePerson",single);
		model.addAttribute("people", pList);
		
		return "indexs";

	}
	
	@RequestMapping("/security")
	public String security(Model model){
		Msg msg=new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg",msg);
		return "home";
		
	}
	
}

