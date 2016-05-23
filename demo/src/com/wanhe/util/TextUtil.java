package com.wanhe.util;


/**
 * 
 * ClassName: TextUtil 
 * @Description: 字符串工具类
 * @author 王程
 * @date 2015-9-9 下午2:06:47
 */
public class TextUtil {
	
	/**
	  * @Description 两个字符串相比较
	  * @param	arg0	 字符串
	  * @param	arg1	字符串
	  * @return	boolean
	  * @author	王程
	  * @Date 2015-9-9 下午2:08:47
	 */
	public static boolean TextEquals(String arg0,String arg1){
		if(arg0 == null){
			return false;
		}
		if(arg0.equals(arg1)){
			return true;
		}
		return false;
	}
	
	/**
	  * @Description 判断字符串非空
	  * @param	arg0	字符串
	  * @return	boolean	
	  * @author 王程
	  * @Date 2015-9-9 下午2:47:15
	 */
	public static boolean TextNotNull(String arg0){
		if(!("".equals(arg0)) && arg0 != null){
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(TextUtil.TextNotNull(null));
	}
}
