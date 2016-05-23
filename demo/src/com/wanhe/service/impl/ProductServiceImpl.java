/*
 * 
 * 
 * 
 */
package com.wanhe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wanhe.dao.ProductDao;
import com.wanhe.entity.Product;
import com.wanhe.service.ProductService;

/**
 * Service - 管理员
 * 
 * 
 * 
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

	@Resource(name = "productDaoImpl")
	private ProductDao productDao;

	@Resource(name = "productDaoImpl")
	public void setBaseDao(ProductDao productDao) {
		super.setBaseDao(productDao);
	}
}