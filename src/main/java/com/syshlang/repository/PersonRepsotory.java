/*
 * Copyright (c) 2018. GRGBanking
 * @File: PersonRepsotory.java
 * @Description:
 * @Author: sunys
 * @Date: 18-6-7 下午9:49
 * @since:
 */

package com.syshlang.repository;


import com.syshlang.entity.Person;
import org.springframework.data.repository.Repository;

public interface PersonRepsotory extends Repository<Person,Integer>{

    //根据 lastName 来获取对应的 Person
    Person getByLastName(String lastName);
}
