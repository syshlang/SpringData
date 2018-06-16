/*
 * Copyright (c) 2018. GRGBanking
 * @File: CommonMethod.java
 * @Description:
 * @Author: sunys
 * @Date: 18-6-17 上午4:54
 * @since:
 */

package com.syshlang.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CommonMethod<T, ID extends Serializable>
	extends JpaRepository<T, ID> {

	void method();
	
}
