/*
 * 
 * 
 * 
 */
package com.wanhe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanhe.dao.OrderDao;
import com.wanhe.entity.Order;
import com.wanhe.service.OrderService;

/**
 * Service - 管理员
 * 
 * 
 * 
 */
@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

	@Resource(name = "orderDaoImpl")
	private OrderDao orderDao;

	@Resource(name = "orderDaoImpl")
	public void setBaseDao(OrderDao orderDao) {
		super.setBaseDao(orderDao);
	}
	
	@Transactional(readOnly = true)
	public Order findBySn(String sn) {
		return orderDao.findBySn(sn);
	}

	@Transactional(readOnly = true)
	public List<Order> findBySplitSn(String splitSn) {
		return orderDao.findBySplitSn(splitSn);
	}
}