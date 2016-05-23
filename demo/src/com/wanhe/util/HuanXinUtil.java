package com.wanhe.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.wanhe.EasemobConfig;



/**
 * 
 * ClassName: HuanXinUtil 
 * @Description: 环信信聊天后台操作工具类
 * @author 王程
 * @date 2015-8-24 下午9:17:36
 */
public class HuanXinUtil {
	
	/**
	  * @Title: register 
	  * @Description: 		注册环信
	  * @param username		用户手机号
	  * @param password		用户密码
	  * @param nickName		昵称
	  * @param type			类型 1：游客 2：导游
	  * @return boolean		是否注册成功
	  * @throws 
	  * @author 王程
	  * @Date 2015-8-24 下午2:41:27
	 */
	public static boolean register(String username, String password, String nickName){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
	        Map<String,String> paramMap = new HashMap<String, String>();
	        paramMap.put("username", username);
	        paramMap.put("password", password);
	        if(!("".equals(nickName)) && nickName != null){
	        	paramMap.put("nickname", nickName);
	        }
			String param = JsonOperate.toJSon(paramMap);
			String result = HttpRequestUtil.sendPost(EasemobConfig.getInstance().get_SERVICE() + "users", param, false, hearderMap);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	  * @Description:       创建群组
	  * @param  groupName	群名称
	  * @param  GroupDesc	群描述
	  * @param  isPublic	是否公开
	  * @param  owner		群主
	  * @param  members		群组成员(可选参数)
	  * @return boolean		环信聊天群号
	  * @throws 	
	  * @author 王程
	  * @Date 2015-8-24 下午2:42:44
	 */
	/*{
	"groupname":"testrestgrp12", //群组名称, 此属性为必须的
	"desc":"server create group", //群组描述, 此属性为必须的
	"public":true, //是否是公开群, 此属性为必须的,为false时为私有群
	"maxusers":300, //群组成员最大数(包括群主), 值为数值类型,默认值200,此属性为可选的
	"approval":true, //加入公开群是否需要批准, 默认值是false（加群不需要群主批准）, 此属性为可选的,只作用于公开群
	"owner":"jma1", //群组的管理员, 此属性为必须的
	"members":["jma2","jma3"] //群组成员,此属性为可选的,但是如果加了此项,数组元素至少一个（注：群主jma1不需要写入到members里面）
	}*/
	public static String createGroup(String groupName,String GroupDesc,boolean isPublic,String owner,String[] members){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
	        Map<String,Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("groupname", groupName);
	        paramMap.put("desc", GroupDesc);
	        paramMap.put("public", isPublic);
	        paramMap.put("owner", owner);
	        if(members != null && members.length > 0){
	        	paramMap.put("members", members);
	        }
	        String param = JsonOperate.toJSon(paramMap);
			String result = HttpRequestUtil.sendPost(EasemobConfig.getInstance().get_SERVICE() + "chatgroups",param,false,hearderMap);
			System.out.println(result);
			
			JSONObject json = new JSONObject(result); 
			String groupId =  json.getJSONObject("data").getString("groupid");
			
			return groupId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	  * @Description:       更新群组
	  * @param  groupName	群名称
	  * @param  GroupDesc	群描述
	  * @throws 	
	  * @author 王程
	  * @Date 2015-8-24 下午2:42:44
	 */
	/*{
	 "groupId":"testrestgrp12", //群组ID, 此属性为必须的
	"groupName":"testrestgrp12", //群组名称, 此属性为必须的
	"desc":"server create group", //群组描述, 此属性为必须的
	}*/
	public static boolean updateGroup(String groupId,String groupName){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
	        Map<String,Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("groupname", groupName);
	        String param = JsonOperate.toJSon(paramMap);
			String result = HttpRequestUtil.sendPut(EasemobConfig.getInstance().get_SERVICE() + "chatgroups/"+groupId,param.toString(),hearderMap);
			System.out.println(result);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	  * @Description:       获得群组
	  * @param  groupName	群名称
	  * @param  GroupDesc	群描述
	  * @throws 	
	  * @author 王程
	  * @Date 2015-8-24 下午2:42:44
	 */
	/*{
	 "groupId":"testrestgrp12", //群组ID, 此属性为必须的
	"groupName":"testrestgrp12", //群组名称, 此属性为必须的
	"desc":"server create group", //群组描述, 此属性为必须的
	}*/
	public static String getGroup(String groupId){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
			String result = HttpRequestUtil.getUserHttpRequest(EasemobConfig.getInstance().get_SERVICE() + "chatgroups/"+groupId, hearderMap);
			System.out.println(result);
			JSONObject json = new JSONObject(result); 
			String data =  json.getString("data"); 	
			String groupid =  data;
			return groupid;
		} catch (Exception e) {
			//e.printStackTrace();
			return "";
		}
	}
	
	/**
	  * @Description:       获得用户
	  * @param  groupName	群名称
	  * @param  GroupDesc	群描述
	  * @throws 	
	  * @author 王程
	  * @Date 2015-8-24 下午2:42:44
	 */
	/*{
	 "username":"testrestgrp12", //群组ID, 此属性为必须的
	"groupName":"testrestgrp12", //群组名称, 此属性为必须的
	"desc":"server create group", //群组描述, 此属性为必须的
	}*/
	public static boolean isUser(String username){
		try {
	        Map<String,String> hearderMap = new HashMap<String, String>();
	           hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
	           String result = HttpRequestUtil.getUserHttpRequest(EasemobConfig.getInstance().get_SERVICE() + "users/"+username,hearderMap);
	           if("".equals(result)){
	           		return false;
	           }
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	  * @Description: 		批量添加用户到群
	  * @param groupId		群ID	
	  * @param users		用户名数组
	  * @return boolean		是否成功
	  * @throws 
	  * @author 王程
	  * @Date 2015-8-24 下午9:11:09
	  * 
	  *  /{org_name}/{app_name}/chatgroups/{group_id}/users
	  *	 {“usernames”:[“username1”,”username2”]}’ — usernames固定属性，作为
	  * 
	 */
	public static boolean addBatchuserToGroup(String groupId,String[] users){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
	        
	        Map<String,String[]> paramMap = new HashMap<String, String[]>();
	        paramMap.put("usernames", users);
	        String param = JsonOperate.toJSon(paramMap);
	        
	        String result = HttpRequestUtil.sendPost(EasemobConfig.getInstance().get_SERVICE()+"chatgroups/"+groupId+"/users", param, false, hearderMap);
	        
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	  * @Title: deleteUserByGroup 
	  * @Description:  从群中移除某个用户
	  * @param chatNo	群号
	  * @param username  用户名
	  * @return boolean
	  * @throws 
	  * @author 王程
	  * @Date 2015-9-30 上午9:47:13
	  * Path : /{org_name}/{app_name}/chatgroups/{group_id}/users/{username}
	  *	HTTP Method : DELETE
	 */
	public static boolean deleteUserByGroup(String chatNo,String username){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
	        
	        String result = HttpRequestUtil.sendDelete(EasemobConfig.getInstance().get_SERVICE() + "chatgroups/"+chatNo+"/users/"+username, null, false, hearderMap);
	        System.out.println(result);
	        if(TextUtil.TextEquals("404",result) || TextUtil.TextEquals("501",result)){
	        	return false;
	        }
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	  * @Title: deleteGroup 
	  * @Description: 删除群
	  * @param chatNo	群号
	  * @return boolean	是否成功
	  * @author 王程
	  * @Date 2015-11-21 下午8:10:23
	  * 
	  *  /{org_name}/{app_name}/chatgroups/{group_id}
	 */
	public static boolean deleteGroup(String chatNo){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
	        
	        String result = HttpRequestUtil.sendDelete(EasemobConfig.getInstance().get_SERVICE() + "chatgroups/" + chatNo, null, false, hearderMap);
	        if(TextUtil.TextEquals("404",result) || TextUtil.TextEquals("501",result)){
	        	return false;
	        }
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
       return false;
	}
	
	/**
	  * @Title: deleteUser 
	  * @Description: 删除环信用户
	  * @param username	环信用户名
	  * @return boolean
	  * @throws 
	  * @author 王程
	  * @Date 2015-9-10 下午1:16:49
	  * 
	  * /{org_name}/{app_name}/users/{username}
	 */
	public static boolean deleteUser(String username){
		try {
			Map<String,String> hearderMap = new HashMap<String, String>();
           hearderMap.put("Authorization","Bearer "+ EasemobConfig.getInstance().getTOKEN_ID());
           
           String result = HttpRequestUtil.sendDelete(EasemobConfig.getInstance().get_SERVICE() + "users/"+username, null, false, hearderMap);
           
           if(TextUtil.TextEquals("404",result)){
           	return false;
           }
           
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
//		createGroup("测试","测试",true,"18583757690",null);
		
		Map<String,Object> hearderMap = new HashMap<String, Object>();
        hearderMap.put("grant_type","client_credentials");
        hearderMap.put("client_id","YXA6Kj5asJ_YEeWIMmOCIr8nJg");
        hearderMap.put("client_secret","YXA6YefF-N6XnociYC_E9LGZ9_5I03A");
        String param = JsonOperate.toJSon(hearderMap);
        
		String result = HttpRequestUtil.sendPost(EasemobConfig.getInstance().get_SERVICE() + "token", param, false, null);
		System.out.println(result);
	}
	
}
