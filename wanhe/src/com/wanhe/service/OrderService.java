/*
 * 
 * 
 * 
 */
package com.wanhe.service;

import java.util.List;

import com.wanhe.entity.Order;

/**
 * Service - 管理员
 * 
 * 
 * 
 */
public interface OrderService extends BaseService<Order, Long> {

	public Order findBySn(String sn);
	
	List<Order> findBySplitSn(String splitSn);
}