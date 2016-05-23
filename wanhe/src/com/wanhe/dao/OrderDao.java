/*
 * 
 * 
 * 
 */
package com.wanhe.dao;

import java.util.List;

import com.wanhe.entity.Order;

/**
 * Dao - 管理员
 * 
 * 
 * 
 */
public interface OrderDao extends BaseDao<Order, Long> {

	public Order findBySn(String sn);
	
	List<Order> findBySplitSn(String splitSn);
}