/*
 * Copyright (c) 2018. GRGBanking
 * @File: PersonRepsotoryImpl.java
 * @Description:
 * @Author: sunys
 * @Date: 18-6-17 上午12:42
 * @since:
 */

package com.syshlang.repository.impl;

import com.syshlang.dao.PersonDao;
import com.syshlang.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepsotoryImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class, 11);
        System.out.println("-->" + person);
    }

}
