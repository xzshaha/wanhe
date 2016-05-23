package com.wanhe.util;


import com.google.gson.Gson;

/**
 * 
 * @ClassName: JsonOperate
 * @Description: json数据操作类
 * @author 胡安全
 * @date 2015年4月28日 上午9:59:48
 */
public class JsonOperate {

	static Gson objectMapper;

	/**
	 * 
	 * @Title: readValue
	 * @Description: 字符串转对象
	 * @param @param content
	 * @param @param valueType
	 * @param @return    设定文件
	 * @return T    返回类型
	 * @throws
	 */
	public static <T> T readValue(String content, Class<T> valueType) {

		if (objectMapper == null) {
			objectMapper = new Gson();
		}

		try {

			return objectMapper.fromJson(content, valueType);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Title: toJSon
	 * @Description: 对象转字符串
	 * @param @param object
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static String toJSon(Object object) {
		if (objectMapper == null) {
			objectMapper = new Gson();
		}

		try {

			return objectMapper.toJson(object);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
