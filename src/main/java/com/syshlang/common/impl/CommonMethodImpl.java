/*
 * Copyright (c) 2018. GRGBanking
 * @File: CommonMethodImpl.java
 * @Description:
 * @Author: sunys
 * @Date: 18-6-17 上午4:54
 * @since:
 */

package com.syshlang.common.impl;

import com.syshlang.common.CommonMethod;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class CommonMethodImpl<T, ID extends Serializable>
	extends SimpleJpaRepository<T, ID> implements CommonMethod<T, ID> {

	private final Class<T> domainClass;

	public CommonMethodImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.domainClass = domainClass;
	}

	@Override
	public void method() {
		System.out.println("...METHOD TEST...");
	}

}
