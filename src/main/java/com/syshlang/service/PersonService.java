/*
 * Copyright (c) 2018. GRGBanking
 * @File: PersonService.java
 * @Description:
 * @Author: sunys
 * @Date: 18-6-17 上午12:18
 * @since:
 */

package com.syshlang.service;

import com.syshlang.entity.Person;
import com.syshlang.repository.PersonRepsotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

	@Autowired
	private PersonRepsotory personRepsotory;
	
	@Transactional
	public void savePersons(List<Person> persons){
		personRepsotory.save(persons);
	}
	
	@Transactional
	public void updatePersonEmail(String email, Integer id){
		personRepsotory.updatePersonEmail(id, email);
	}
}
