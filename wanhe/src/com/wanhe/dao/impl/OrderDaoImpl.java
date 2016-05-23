/*
 * 
 * 
 * 
 */
package com.wanhe.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wanhe.dao.OrderDao;
import com.wanhe.entity.Order;

/**
 * Dao - 管理员
 * 
 * 
 * 
 */
@Repository("orderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {

	public Order findBySn(String sn) {
		if (sn == null) {
			return null;
		}
		String jpql = "select orders from Order orders where lower(orders.sn) = lower(:sn)";
		try {
			return entityManager.createQuery(jpql, Order.class).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Order> findBySplitSn(String splitSn) {
		if (splitSn == null) {
			return null;
		}
		String jpql = "select orders from Order orders where lower(orders.splitSn) = lower(:splitSn)";
		try {
			return entityManager.createQuery(jpql, Order.class).setFlushMode(FlushModeType.COMMIT).setParameter("splitSn", splitSn).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}