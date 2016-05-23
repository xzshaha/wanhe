/*
 * 
 * 
 * 
 */
package com.wanhe.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wanhe.dao.AdminDao;
import com.wanhe.entity.Admin;

/**
 * Dao - 管理员
 * 
 * 
 * 
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {

	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username) AND isEnabled = true";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}
	
	public Admin findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username) AND isEnabled = true";
			return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}