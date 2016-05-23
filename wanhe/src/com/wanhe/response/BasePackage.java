package com.wanhe.response;

import java.io.Serializable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 数据组装基类
 *
 * @param <T>
 */
public abstract class BasePackage<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -331898806638998276L;

	/**
	 * 封装对象
	 */
	protected JSONObject json;
	
	/**
	 * 封装集合
	 */
	protected JSONArray array;
	
	/**
	 * 封装数据
	 */
	public abstract JSONObject assemble(T entity, Object... objects);
	
	/**
	 * 验证数据是否为NULL
	 */
	protected Object isNull(Object value){
		if(value == null){
			return "";
		}
		return value;
	}
	
}
