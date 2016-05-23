/*
 * 
 * 
 * 
 */
package com.wanhe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanhe.dao.PluginConfigDao;
import com.wanhe.entity.PluginConfig;
import com.wanhe.service.PluginConfigService;

/**
 * Service - 插件配置
 * 
 * 
 * 
 */
@Service("pluginConfigService")
public class PluginConfigServiceImpl extends BaseServiceImpl<PluginConfig, Long> implements PluginConfigService {

	@Resource(name = "pluginConfigDaoImpl")
	private PluginConfigDao dao;

	@Resource(name = "pluginConfigDaoImpl")
	public void setBaseDao(PluginConfigDao dao) {
		super.setBaseDao(dao);
	}

	@Transactional(readOnly = true)
	public boolean pluginIdExists(String pluginId) {
		return dao.pluginIdExists(pluginId);
	}

	@Transactional(readOnly = true)
	public PluginConfig findByPluginId(String pluginId) {
		return dao.findByPluginId(pluginId);
	}

}