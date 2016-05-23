/*
 * 
 * 
 * 
 */
package com.wanhe.dao.impl;

import org.springframework.stereotype.Repository;

import com.wanhe.dao.ProductDao;
import com.wanhe.entity.Product;

/**
 * Dao - 管理员
 * 
 * 
 * 
 */
@Repository("productDaoImpl")
public class ProductDaoImpl extends BaseDaoImpl<Product, Long> implements ProductDao {

}