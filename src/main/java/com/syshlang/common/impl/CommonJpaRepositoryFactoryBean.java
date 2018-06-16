/*
 * Copyright (c) 2018. GRGBanking
 * @File: CommonJpaRepositoryFactoryBean.java
 * @Description:
 * @Author: sunys
 * @Date: 18-6-17 上午4:54
 * @since:
 */

package com.syshlang.common.impl;

import com.syshlang.common.CommonMethod;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class CommonJpaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new CommonRepositoryFactory(entityManager);
	}

	private static class CommonRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager entityManager;

		public CommonRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@Override
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new CommonMethodImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
		}


		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return CommonMethod.class;
		}
	}

}
